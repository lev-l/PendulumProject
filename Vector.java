public class Vector {
    private double xComponent;
    private double yComponent;
    private double magnitude;

    // Creates a representation of a vector quantity with x and y components.
    public Vector(double xComponent, double yComponent) {
        this.xComponent = xComponent;
        this.yComponent = yComponent;
        this.magnitude = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
    }

    // Creates a vector based on magnitude and angle from horizontal.
    public Vector(double magnitude, long angle){
        this.magnitude = magnitude;
        this.xComponent = magnitude * Math.cos(angle);
        this.yComponent = magnitude * Math.sin(angle);
    }

    public double getX() {
        return xComponent;
    }

    public double getY() {
        return yComponent;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public Vector add(Vector other){
        return new Vector(xComponent + other.getX(), yComponent + other.getY());
    }

    public Vector multiply(double scalar){
        return new Vector(xComponent * scalar, yComponent * scalar);
    }

    public String toString(){
        return "{" + xComponent + ", " + yComponent + "}";
    }
}