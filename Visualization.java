import java.awt.BorderLayout;
import javax.swing.*;

public class Visualization {
    private JFrame frame;
    private PendulumPanel pendulumPanel;

    public Visualization(Pendulum pendulum)
    {
        frame = new JFrame("Pendulum Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);

        pendulumPanel = new PendulumPanel(pendulum);
        frame.add(pendulumPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void update() {
        pendulumPanel.repaint();
    }
}
