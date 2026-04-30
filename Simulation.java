public class Simulation {
    public static void main(String[] args) {
        Pendulum pendulum = new Pendulum(1.0, new Vector(0, 0), Math.PI / 4, 1.0);
        double timeStep = 0.01; // 10 milliseconds per update.
        System.out.println("Initial position: " + pendulum.getPosition());
        System.out.println("Initial inclination: " + pendulum.getInclination());

        pendulum.update(timeStep);
        System.out.println("Position after first update: " + pendulum.getPosition());
        System.out.println("Inclination after first update: " + pendulum.getInclination());

        // Simulates quorter of the period for the initial conditions.
        double elapsedTime = timeStep;
        while(elapsedTime < 0.52){
            pendulum.update(timeStep);
            System.out.println(pendulum.getInclination() * 180 / Math.PI);
            elapsedTime += timeStep;        
        }

        System.out.println("Final position: " + pendulum.getPosition());
        System.out.println("Final inclination: " + pendulum.getInclination()); // * 180 / Math.PI);
        if(Math.round(pendulum.getPosition().getMagnitude()) != 1.0){
            System.out.println("Error in the pendulum's length");
        }
    }
}
