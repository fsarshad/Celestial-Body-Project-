import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CelestialBodyWrapper {
    // gravity constant
    private static final double G = 6.67408e-11;
    private List<CelestialBody> bodies;
    private double metersPerPixel;

    public void readFile(String filename) throws IOException {
        String line;
        String splitBy = ",";

        Scanner scanner = new Scanner(new File(filename));

        // Detect list type on first line
        String listType = scanner.next();
        if (listType.equals("ArrayList")) {
            bodies = new ArrayList<>();
        } else {
            bodies = new LinkedList<>();
        }

        // Detect meters per pixel scale on second line
        metersPerPixel = scanner.nextDouble();

        // Set the delimiter of the scanner to ","
        scanner.useDelimiter("[,\\s\n]");

        // lines to read in the file...
        while (scanner.hasNextLine()) {
            String name = scanner.next();
            System.out.println("Name: " + name);
            double mass = scanner.nextDouble();
            int xPos = scanner.nextInt();
            int yPos = scanner.nextInt();
            double xVel = scanner.nextDouble() * metersPerPixel;
            double yVel = scanner.nextDouble() * metersPerPixel;
            int size = scanner.nextInt();
            CelestialBody body = new CelestialBody(name, mass, xPos, yPos, xVel, yVel, size);
            bodies.add(body);
        }
        scanner.close();
    }

    // Waits for some time to slow down the animation
    private void delay (){
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Animates the simulation
    public void animate() {
        Window window = new Window(bodies);

// Render/Physics loop

        // delta t is in seconds
        double dt = 86400;

        while (true) {
            delay();
            for (int i = 0; i < bodies.size(); i++) {
                CelestialBody body = bodies.get(i);

                double netXAcc = 0;
                double netYAcc = 0;
                for (int j = 0; j < bodies.size(); j++) {
                    CelestialBody otherBody = bodies.get(j);
                    if (otherBody == body) continue;

                    // The distance on the x axis between the 2 bodies
                    double xDif = (otherBody.getXPos() - body.getXPos()) * metersPerPixel;

                    // The distance on the y axis between the 2 bodies
                    double yDif = (otherBody.getYPos() - body.getYPos()) * metersPerPixel;

                    // Mass of "body"
                    double m1 = body.getMass();

                    // Mass of "otherBody"
                    double m2 = otherBody.getMass();

                    // Distance between the 2 bodies
                    // meters
                    double r2 = xDif * xDif + yDif * yDif;

                    // Newtons
                    double f = G * m1 * m2 / r2;

                    // Used Newton's 2nd law, F=ma, found acceleration between 2 bodies
                    // a = F/m , Acceleration between body and otherBody
                    // m/s^2
                    double a = f / m1;

                    // System.out.println("F="+f+" m="+m1+" ,a= "+a);
                    // Direction on the x axis that the acceleration should pull "body" in.
                    // double xDir = xDif/r;
                    // Direction on the y axis that the acceleration should pull "body" in.
                    // double yDir = yDif/r;

                    // Adding the x acceleration to the net acceleration that add to the body's velocity at the end
                    double r = Math.sqrt(r2);
                    netXAcc += a * xDif / r;

                    // Adding the y acceleration to the net acceleration that add to the body's velocity at the end
                    netYAcc += a * yDif / r;
                }

                // netXAcceleration to the x velocity of body
                // netYAcceleration to the y velocity of body
                body.setXVel(body.getXVel() + netXAcc * dt);
                body.setYVel(body.getYVel() + netYAcc * dt);

                // scaling factor for pixel vs meters.
                double dx = body.getXVel() * dt;
                body.setX(body.getXPos() + dx / metersPerPixel);

                double dy =  body.getYVel() * dt;
                body.setY(body.getYPos() + dy / metersPerPixel);

                // body.setX(0.5*netXAcc*dt*dt+body.getXPos());
                // body.setY(0.5*netYAcc*dt*dt+body.getYPos());
            }
            window.repaint();
        }
    }
}


