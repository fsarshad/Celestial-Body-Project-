import java.io.*;

public class Main {

    public static void main(String[] args)throws IOException
    {
        CelestialBodyWrapper wr = new CelestialBodyWrapper();
        wr.readFile("NBody.csv");
        wr.animate();
    }
}

