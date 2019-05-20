package moleculesampleapp;

public class Vector3 {
    double x;
    double y;
    double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Vector3 other) {
        return other.x == x && other.y == y && other.z == z;
    }

    public void plus(Vector3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
    }
}
