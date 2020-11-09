import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

// Celestial Body Extends JPanel
public class CelestialBody extends JPanel {
    private String name;
    private double mass;
    // these are in pixels
    private double x, y;
    private double xVel;
    private double yVel;

    // Qualities of the Celestial Body
    public CelestialBody(String name, double mass, int x, int y, double xVel, double yVel, int size) {
        this.name = name;
        this.mass = mass;
        this.xVel = xVel;
        this.yVel = yVel;

        setSize(size, size);
        setX(x);
        setY(y);
    }

    // Returns name
    public String getName() {
        return name;
    }

    // Returns mass
    public double getMass() {
        return mass;
    }

    // Name is set
    public void setName(String name) {
        this.name = name;
    }

    // Set Mass methods
    public void setMass(double mass) {
        this.mass = mass;
    }

    // Returns x
    public double getXPos() {
        return x;
    }

    // Returns y
    public double getYPos() {
        return y;
    }

    // Returns xVelocity
    public double getXVel() {
        return xVel;
    }

    // Returns yVelocity
    public double getYVel() {
        return yVel;
    }

    // xVelocity is set
    public void setXVel(double xVel) {
        this.xVel = xVel;
    }

    // yVelocity is set
    public void setYVel(double yVel) {
        this.yVel = yVel;
    }

    // X location is set, casting to int. in pixels.
    public void setX(double x) {
        this.x = x;
        setLocation((int) x, (int) getYPos());
    }

    // Y location is set, casting to int. in pixels.
    public void setY(double y) {
        this.y = y;
        setLocation((int) getXPos(), (int) y);
    }

    // To string method returns the methods etc.
    public String toString() {
        return "[name=" + name + "x=" + getX() + ",y=" + getY() + ",xVel=" + getXVel() + ",getYVel=" + getYVel() + "]";
    }

    // Celestial Body is Rendered.
    // Paint component method.
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int size = getSize().width;
        g2d.setPaint(Color.BLACK);
        g2d.fillOval(0, 0, size, size);
        g2d.drawString(name, 0, 0);
    }
}
