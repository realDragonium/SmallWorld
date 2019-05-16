package moleculesampleapp;

public class Rotation {
	
	double x;
	double y;
	double z;
	
	public Rotation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
	
	public void plus(Rotation other) {
		x += other.x;
		y += other.y;
		z += other.z;
	}
}
