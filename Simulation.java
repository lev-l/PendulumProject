public class Simulation {
    public static void main(String[] args) {
        Pendulum pendulum = new Pendulum(1.0, new Vector(0, 0), Math.PI / 4, 1.0);
        double timeStep = 0.01; // 10 milliseconds per update.
    
        System.out.println("Initial position: " + pendulum.getPosition());
    }
}
