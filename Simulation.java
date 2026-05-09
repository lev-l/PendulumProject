import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation {
    private static Timer timer; // The loop timer
    private static Pendulum pendulum; // Main physical object
    private static Visualization visual; // Simulation window
    private static long elapsedTime; // How many milliseconds since start of simulation
    private static long timeStep = 1; // 1 millisecond
    private static double lastInclination; // Used to calculate the max inclination and the inclination time derivative
    private static double maxInclination; // The amplitude of the pendulum
    private static double lastInclinationRate; // The previous value of the inclination time derivative
    private static long lastUpdateTime; // The last time the period was updated, used to find a new period
    private static double period; // The period of the pendulum
    private static ArrayList<Vector> pathTrail = new ArrayList<Vector>(); // Stores path points
    private static boolean isRunning; // Indicates whether simulation timer is on

    public static void main(String[] args) {
        setup();

        // Starts the simulation window
        visual = new Visualization(pendulum);
        resume();
    }

    // Stops the simulation loop
    public static void pause(){
        isRunning = false;
        timer.cancel();
    }

    // Starts a new loop
    public static void resume(){
        // Prevents multiple timers
        if(isRunning) return;

        isRunning = true;
        // Starts the simulation (physics and graphics on the same update)
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                pendulum.update(timeStep / 1000.0); // ms to seconds

                /* Each time the derivative of inclination changes sign,
                *  the pendulum has completed half a period.
                *  So, period is calculated as double the time it took for that to happen.
                */
                double inclinationRate = (pendulum.getInclination() - lastInclination) / (timeStep / 1000.0);
                if(inclinationRate * lastInclinationRate < 0){
                    period = (elapsedTime - lastUpdateTime) * 2 / 1000.0;
                    lastUpdateTime = elapsedTime;
                    // Sets the max inclination to the inclination reached at the inflection point
                    maxInclination = pendulum.getInclination();
                }
                // Updates the last state to current state
                lastInclinationRate = inclinationRate;
                lastInclination = pendulum.getInclination();

                // Debug outputs and path tracking every 0.1 seconds
                if(elapsedTime % 50 == 0){
                    pathTrail.add(pendulum.getPosition());
                    if(pathTrail.size() > 50){
                        pathTrail.remove(0);
                    }
                }
                
                // Updates the data outputs and graphics
                visual.update(period, Math.round(Math.abs(maxInclination * 180 / Math.PI)));
                elapsedTime += timeStep;
            }
        }, 0, timeStep);
    }

    // Stops the current simulation, resets all values, and starts again
    public static void reset(){
        pause();
        setup();
        resume();
    }

    public static void setup(){
        // Initializes the pendulum
        pendulum = new Pendulum(1.0, new Vector(0, 0),
                                        new Vector(0, 0), Math.PI / 4, 1.0);

        // Sets all valuse to initial and gets some first data points to correctly calculate the period in the main loop
        elapsedTime = 0;
        pendulum.update(timeStep / 1000.0);
        lastInclinationRate = (pendulum.getInclination() - lastInclination) / (timeStep / 1000.0);
        lastInclination = pendulum.getInclination();
        maxInclination = lastInclination;
        lastUpdateTime = elapsedTime;
        period = 0.0;
    }

    public static long getElapsedTime() {
        return elapsedTime;
    }

    // Needed for the visualization to have access to the physical model's state
    public static Pendulum getPendulum() {
        return pendulum;
    }

    public static ArrayList<Vector> getPathTrail() {
        return pathTrail;
    }
}
