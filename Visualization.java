import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Visualization {
    private JTextArea textDataOutput; 
    private PendulumPanel pendulumPanel;
    private double period;

    public Visualization(Pendulum pendulum)
    {
        // Main window
        JFrame frame = new JFrame("Pendulum Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // Panel to draw the pendulum
        pendulumPanel = new PendulumPanel();
        frame.add(pendulumPanel, BorderLayout.CENTER);

        // Bottom panel with data output
        JPanel dataPanel = new JPanel();
        textDataOutput = new JTextArea("Period: " + period + " seconds;    Amplitude: 0.0;");
        textDataOutput.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        dataPanel.add(textDataOutput, BorderLayout.CENTER);
        frame.add(dataPanel, BorderLayout.SOUTH);

        // Buttons to control the simulation
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 0, 10, 20));
        // Button to pause the simulation
        JButton pauseButton = new JButton("PAUSE");
        pauseButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulation.pause();
            }
        });
        // Button to resume the simulation
        JButton resumeButton = new JButton("RESUME");
        resumeButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulation.resume();
            }
        });
        // Button to reset the simuation
        JButton resetButton = new JButton("RESET");
        resetButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulation.reset();
            }
        });

        buttonsPanel.add(pauseButton);
        buttonsPanel.add(resumeButton);
        buttonsPanel.add(resetButton);
        frame.add(buttonsPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    public void update(double period, double inclination) {
        this.period = period;
        textDataOutput.setText("Period: " + period + " seconds;    Amplitude: " + inclination + " degrees;");
        pendulumPanel.repaint();
    }
}
