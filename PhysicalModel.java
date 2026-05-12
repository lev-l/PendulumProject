public class PhysicalModel {
    private double mass;
    private Vector position;
    private Vector velocity;

    public PhysicalModel(){
        mass = 1.0;
        position = new Vector(0, 0);
        velocity = new Vector(0, 0);
    }

    // Creates a simplistic representation of a physical object.
    public PhysicalModel(double mass, Vector position, Vector velocity){
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
    }

    public double getMass() {
        return mass;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    // Calculates the net force on the object from all forces acting on it.
    public Vector calculateNetForce(Vector[] forces){
        Vector net = new Vector(0, 0);
        
        for(Vector force : forces){
            net = force.add(net);
        }

        return net;
    }

    // Finds object's acceleration from Newton's second law.
    public Vector calculateAcceleration(Vector netForce){
        return netForce.multiply(1 / mass);
    }

    // Integrates acceleration (assumes small dTime).
    public Vector updateVelocity(Vector acceleration, double dTime){
        velocity = velocity.add(acceleration.multiply(dTime));
        return velocity;
    }

    // Integrates velocity and updates position (assumes small dTime), returns new position.
    public Vector updatePosition(Vector velocity, double dTime){
        position = position.add(velocity.multiply(dTime));
        return position;
    }

    // Updates mass of the pendulum preserving momentum
    public void updateMass(double newMass){
        velocity = velocity.multiply(mass).multiply(1/newMass);
        mass = newMass;
    }

    protected void setPosition(Vector newPosition){
        this.position = newPosition;
    }
}