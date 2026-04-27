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

    public double getX() {
        return xComponent;
    }

    public double getY() {
        return yComponent;
    }

    public double getMagnitude() {
        return magnitude;
    }
}