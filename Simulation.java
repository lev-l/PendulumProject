import java.util.Timer;
import java.util.TimerTask;

public class Simulation {
    private static long elapsedTime;
    private static long timeStep = 1; // 1 millisecond

    public static void main(String[] args) {

        Pendulum pendulum = new Pendulum(1.0, new Vector(0, 0),
                                        new Vector(0, 0), Math.PI / 4, 1.0);
        System.out.println("Time steps in simulation " + timeStep + " ms");
        System.out.println("Initial position: " + pendulum.getPosition());
        System.out.println("Initial inclination: " + pendulum.getInclination());

        Visualization visual = new Visualization(pendulum);
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                pendulum.update(timeStep / 1000.0); // ms to seconds
                visual.update();
                elapsedTime += timeStep;

                if(elapsedTime % 100 == 0){
                    System.out.println("Inclination at " + elapsedTime + " ms: " + pendulum.getInclination() * 180 / Math.PI);
                }
            }
        }, 0, timeStep);

        //pendulum.update(timeStep);
        //System.out.println("Position after first update: " + pendulum.getPosition());
        //System.out.println("Inclination after first update: " + pendulum.getInclination());

        // Simulates quorter of the period for the initial conditions.
        //double elapsedTime = timeStep;
        //while(elapsedTime < 0.52){
        //    pendulum.update(timeStep);
        //    System.out.println(pendulum.getInclination() * 180 / Math.PI);
        //    elapsedTime += timeStep;        
        //}

        //System.out.println("Final position: " + pendulum.getPosition());
        //System.out.println("Final inclination: " + pendulum.getInclination()); // * 180 / Math.PI);
        //if(Math.round(pendulum.getPosition().getMagnitude()) != 1.0){
        //    System.out.println("Error in the pendulum's length");
        //}

        //while(elapsedTime < 0.52 * 4){
        //    pendulum.update(timeStep);
        //    System.out.println(pendulum.getInclination() * 180 / Math.PI);
        //    elapsedTime += timeStep;
        //}

        //System.out.println("Final position: " + pendulum.getPosition());
    }
}
