public class Simulation {
    public static void main(String[] args) {
        Pendulum pendulum = new Pendulum(1.0, new Vector(0, 0), Math.PI / 4, 1.0);
        double timeStep = 0.01; // 10 milliseconds per update.
        System.out.println("Initial position: " + pendulum.getPosition());

        // Simulates quorter of the period for the initial conditions.
        double elapsedTime = 0.0;
        while(elapsedTime < 0.52){
            pendulum.update(timeStep);
            elapsedTime += timeStep;        
        }

        System.out.println("Final position: " + pendulum.getPosition());
        if(Math.round(pendulum.getPosition().getMagnitude()) != 1.0){
            System.out.println("Error in the pendulum's length");
        }
    }
}
