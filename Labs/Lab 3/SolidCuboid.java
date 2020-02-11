public class SolidCuboid extends Cuboid {

	protected final double density;

	public SolidCuboid(double height, double width, double length, double density) {
		super(height, width, length);
		this.density = density;
	}

	@Override
	public SolidCuboid setHeight(double height) {
		return new SolidCuboid(height, this.width, this.length, this.density);
	}

	@Override
	public SolidCuboid setWidth(double width) {
		return new SolidCuboid(this.height, width, this.length, this.density);
	}

	@Override
	public SolidCuboid setLength(double length) {
		return new SolidCuboid(this.height, this.width, length, this.density);
	}


	public double getDensity() {
		return this.density;
	}

	public double getMass() {
		return this.density*super.getVolume();
	}

	@Override
	public String toString() {
		return String.format("SolidCuboid [%.2f x %.2f x %.2f] with a mass of %.2f", this.height, this.width, this.length, this.getMass());
	}
}
