public class SolidSphere extends Sphere {
	protected final double density;

	public SolidSphere(double radius, double density) {
		super(radius);
		this.density = density;
	}

	@Override
	public SolidSphere setRadius(double radius) {
		return new SolidSphere(radius, this.density);
	}

	public double getDensity() {
		return this.density;
	}

	public double getMass() {
		return this.density*super.getVolume();
	}

	@Override
	public String toString() {
		return String.format("SolidSphere [%.2f] with a mass of %.2f", this.radius, this.getMass());
	}

}
