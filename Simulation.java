import java.util.Timer;
import java.util.TimerTask;

public class Simulation {
    private static Timer timer;
    private static Pendulum pendulum;
    private static Visualization visual;
    private static long elapsedTime;
    private static long timeStep = 1; // 1 millisecond
    private static double lastInclination;
    private static double lastInclinationRate;
    private static double maxInclination;
    private static long lastUpdateTime;
    private static double period;

    private static boolean isRunning;

    public static void main(String[] args) {

        // Initializes the pendulum
        pendulum = new Pendulum(1.0, new Vector(0, 0),
                                        new Vector(0, 0), Math.PI / 4, 1.0);
        
        // Initial state printout
        System.out.println("Time steps in simulation " + timeStep + " ms");
        System.out.println("Initial position: " + pendulum.getPosition());
        System.out.println("Initial inclination: " + pendulum.getInclination());

        // Sets all valuse to initial and gets some first data points to correctly calculate the period in the main loop
        elapsedTime = 0;
        pendulum.update(timeStep / 1000.0);
        lastInclinationRate = (pendulum.getInclination() - lastInclination) / (timeStep / 1000.0);
        lastInclination = pendulum.getInclination();
        maxInclination = lastInclination;
        lastUpdateTime = elapsedTime;
        period = 0.0;

        // Starts the simulation window
        // If the simulation is reset, prevents creation of a new window
        if(args.length > 0 && args[0].equals("RESET")){}
        else{
            visual = new Visualization(pendulum);
        }

        resume();
    }

    public static void pause(){
        isRunning = false;
        timer.cancel();
    }

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
                    // Sets the max inclination to amplitude
                    maxInclination = pendulum.getInclination();
                }
                // Updates the last state to current state
                lastInclinationRate = inclinationRate;
                lastInclination = pendulum.getInclination();
                
                // Updates the data outputs and graphics
                visual.update(period, Math.round(Math.abs(maxInclination * 180 / Math.PI)));
                elapsedTime += timeStep;

                // Debug outputs every 0.1 seconds
                if(elapsedTime % 100 == 0){
                    System.out.println("At time: " + elapsedTime + " ms, calculated period " + period + " seconds");
                    System.out.println("Inclination at " + elapsedTime + " ms: " + pendulum.getInclination() * 180 / Math.PI);
                    System.out.println("---");
                }
            }
        }, 0, timeStep);
    }

    public static void reset(){
        pause();
        main(new String[]{"RESET"});
    }
}
