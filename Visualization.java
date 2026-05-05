import javax.swing.*;

public class Visualization {
    private JFrame frame;

    public Visualization()
    {
        frame = new JFrame("Pendulum Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
