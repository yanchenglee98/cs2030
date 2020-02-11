public class Sphere extends Shape3D {
	protected final double radius;

	public Sphere(double radius) {
		this.radius = radius;
	}

	public Sphere setRadius(double radius) {
		return new Sphere(radius);
	}

	@Override
	public double getVolume() {
		return ((double)4/3)*Math.PI*radius*radius*radius;
	}

	@Override
	public double getSurfaceArea() {
		return 4*Math.PI*radius*radius;
	}

	@Override 
	public String toString() {
		return String.format("Sphere [%.2f]", this.radius);
	}
}
