import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

public class PendulumPanel extends JPanel {
    private final double METERS_TO_PIXELS = 200.0;
    private final double MASS_DENSITY = 750.0; // kg/m^3
    private boolean showPath = false;

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
        // Finds the radius of a sphere with the given mass -> cube root of 3m / (4*pi*rho)
        double radius = Math.pow(3.0 * pendulum.getMass() / (4.0 * Math.PI * MASS_DENSITY),
                                1.0/3.0);
        radius *= METERS_TO_PIXELS;
        g2.fill(new Ellipse2D.Double(pivotCenter.getX() + pendulum.getPosition().getX() * METERS_TO_PIXELS - radius,
                                        pivotCenter.getY() + pendulum.getPosition().getY() * METERS_TO_PIXELS - radius,
                                        radius*2, radius*2));

        // Draws the rod from the pivot to pendulum's position.
        g2.draw(new Line2D.Double(pivotCenter.getX(), pivotCenter.getY(),
                                pendulumCenter.getX(), pendulumCenter.getY()));

        // Draws all points in the path trail (if enabled)
        if(showPath){
            g2.setPaint(Color.RED);
            ArrayList<Vector> pathTrail = Simulation.getPathTrail();
            for(int i = 0; i < pathTrail.size(); i++){
                g2.fill(new Ellipse2D.Double(pivotCenter.getX() + pathTrail.get(i).getX() * METERS_TO_PIXELS - 2.5,
                                            pivotCenter.getY() + pathTrail.get(i).getY() * METERS_TO_PIXELS - 2.5,
                                            5, 5));
            }
        }
    }

    public void switchPathTrail(){
        showPath = !showPath;
    }
}