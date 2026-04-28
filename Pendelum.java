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
        Vector relativePosition = new Vector(length * Math.cos(inclination), length * Math.sin(inclination));
        return pivotPosition.add(relativePosition);
    }
}