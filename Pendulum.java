public class Pendulum extends PhysicalModel {
    private Vector pivotPosition; // The fixed position of the pivot.
    private double inclination; // The angle from the vertical, radians.
    private double length; // The length of the pendulum rod.

    public Pendulum(double mass, Vector pivotPosition, double initialInclination, double length){
        super(mass, translateWeightPosition(initialInclination, length, pivotPosition));
        this.pivotPosition = pivotPosition;
        this.inclination = initialInclination;
        this.length = length;
    }

    public Vector getPivotPosition(){
        return pivotPosition;
    }

    public double getInclination(){
        return inclination;
    }

    public double getLength(){
        return length;
    }

    // Finds the position of the pendulum's weight based on pivot position, inclination, and length.
    private static Vector translateWeightPosition(double inclination, double length, Vector pivotPosition){
        Vector relativePosition = new Vector(length * Math.sin(inclination), length * Math.cos(inclination));
        return pivotPosition.add(relativePosition);
    }

    // Updates the pendulum's state based on current state.
    public void update(double dTime){
        // Calculates forces on the pendulum.
        Vector gravityForce = new Vector(0, -9.81 * getMass());
        // Finds the tension force equal and opposite to the component of gravity along the rod.   
        double gravityAlongRodMagnitude = gravityForce.getMagnitude() * Math.cos(inclination);
        Vector tensionForce = new Vector(new double[]{gravityAlongRodMagnitude, inclination + Math.PI / 2});
        Vector[] forces = new Vector[]{gravityForce, tensionForce};

        // Finds the state variables (net force, acceleration, velocity) and updates position.
        Vector netForce = calculateNetForce(forces);
        Vector acceleration = calculateAcceleration(netForce);
        Vector velocity = calculateVelocity(acceleration, dTime);
        Vector angularVelocity = velocity.multiply(1 / length); // Vertical to angular by w = v / r.
        updatePosition(angularVelocity, dTime);
    }

    // Increments inclination and updates position based on angular velocity and return new position.
    public Vector updatePosition(Vector velocity, double dTime){
        inclination += velocity.getMagnitude() * dTime * Math.signum(velocity.getX());
        setPosition(translateWeightPosition(inclination, length, pivotPosition));
        return getPosition();
    }
}