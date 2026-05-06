import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

public class Visualization {
    private JFrame frame;
    private JTextArea textDataOutput;
    private PendulumPanel pendulumPanel;
    private double period;

    public Visualization(Pendulum pendulum)
    {
        frame = new JFrame("Pendulum Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        pendulumPanel = new PendulumPanel(pendulum);
        frame.add(pendulumPanel, BorderLayout.CENTER);

        JPanel dataPanel = new JPanel();
        textDataOutput = new JTextArea("Period: " + period + " seconds;    Amplitude: 0.0;");
        textDataOutput.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        dataPanel.add(textDataOutput, BorderLayout.CENTER);
        frame.add(dataPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void update(double period, double inclination) {
        this.period = period;
        textDataOutput.setText("Period: " + period + " seconds;    Amplitude: " + inclination + " degrees;");
        pendulumPanel.repaint();
    }
}
