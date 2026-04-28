public class PhysicalModel {
    private double mass;
    private Vector position;

    public PhysicalModel(){
        mass = 1.0;
        position = new Vector(0, 0);
    }

    // Creates a simplistic representation of a physical object.
    public PhysicalModel(double mass, Vector position) {
        this.mass = mass;
        this.position = position;
    }

    public double getMass() {
        return mass;
    }

    public Vector getPosition() {
        return position;
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
    public Vector calculateVelocity(Vector acceleration, double dTime){
        return acceleration.multiply(dTime);
    }

    // Integrates velocity and updates position (assumes small dTime).
    public void updatePosition(Vector velocity, double dTime){
        position = position.add(velocity.multiply(dTime));
    }

    protected void setPosition(Vector newPosition){
        this.position = newPosition;
    }
}