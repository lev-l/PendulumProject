<<<<<<< HEAD
import javax.swing.*;
=======
import java.awt.*;
>>>>>>> 814ff5528db74aebab60ddcd7741c2b4d7ccda80

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
