import java.lang.annotation.Inherited;

public class Pendelum extends PhysicalModel {
    private Vector pivotPosition; // The fixed position of the pivot.
    private double inclination; // The angle from the vertical, radians.
    private double length; // The length of the pendulum rod.

    public Pendelum(double mass, Vector pivotPosition, double initialInclination, double length){
        super(mass, translateWeightPosition(initialInclination, length, pivotPosition));
        this.pivotPosition = pivotPosition;
        this.inclination = initialInclination;
        this.length = length;
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
        Vector tensionForce = new Vector(-gravityForce.getMagnitude() * Math.tan(inclination), -gravityForce.getY());
        Vector[] forces = new Vector[]{gravityForce, tensionForce};

        // Finds the state variables (net force, acceleration, velocity) and updates position.
        Vector netForce = calculateNetForce(forces);
        Vector acceleration = calculateAcceleration(netForce);
        Vector velocity = calculateVelocity(acceleration, dTime);
        Vector angularVelocity = velocity.multiply(1 / length); // Vertical to angular by w = v / r.
        updatePosition(angularVelocity, dTime);
    }

    // Increments inclination and updates position based on angular velocity.
    public void getPosition(Vector velocity, double dTime){
        inclination += velocity.getMagnitude() * dTime;
        setPosition(translateWeightPosition(inclination, length, pivotPosition));
    }
}