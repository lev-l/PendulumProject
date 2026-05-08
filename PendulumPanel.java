import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class PendulumPanel extends JPanel {
    private final double METERS_TO_PIXELS = 200.0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Pendulum pendulum = Simulation.getPendulum();
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setPaint(Color.BLACK);
        g2.setStroke(new BasicStroke(2));

        // Draws the pivot and stores its center for future reference
        double centerX = getWidth() / 2.0;
        double yOffset = 50;
        Vector pivotCenter = new Vector(centerX, yOffset + 10);
        g2.fill(new Rectangle2D.Double(centerX - 10, yOffset, 20, 20));

        // Draws the pedndulum's mass and store its center for future reference.
        Vector pendulumCenter = new Vector(pivotCenter.getX() + pendulum.getPosition().getX() * METERS_TO_PIXELS,
                                        pivotCenter.getY() + pendulum.getPosition().getY() * METERS_TO_PIXELS);
        g2.fill(new Ellipse2D.Double(pivotCenter.getX() + pendulum.getPosition().getX() * METERS_TO_PIXELS - 25,
                                        pivotCenter.getY() + pendulum.getPosition().getY() * METERS_TO_PIXELS - 25,
                                        50, 50));

        // Draws the rod from the pivot to pendulum's position.
        g2.draw(new Line2D.Double(pivotCenter.getX(), pivotCenter.getY(),
                                pendulumCenter.getX(), pendulumCenter.getY()));
    }
}