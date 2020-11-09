import javax.swing.JFrame;
import javax.swing.JPanel;


// Window extends JFrame
@SuppressWarnings("serial")
public class Window extends JFrame {
    private JPanel panel;
    private List<CelestialBody> bodies;


    // Created the Window
    public Window(List<CelestialBody> bodies) {
        this.bodies = bodies;

        setTitle("Stars");
        setSize(768, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(768, 768);

        setContentPane(panel);

        // Adding each CelestialBody as a component to this Window
        for (int i = 0; i < bodies.size(); i++) {
            CelestialBody body = bodies.get(i);
            panel.add(body);
        }

        setVisible(true);
    }
}
