public class Cuboid extends Shape3D {
	protected final double height;
	protected final double width;
	protected final double length;
	
	public Cuboid(double height, double width, double length) {
		this.height = height;
		this.width = width;
		this.length = length;
	}

	public Cuboid setHeight(double height) {
		return new Cuboid(height, this.width, this.length);
	}

	public Cuboid setWidth(double width) {
		return new Cuboid(this.height, width, this.length);
	}

	public Cuboid setLength(double length) {
		return new Cuboid(this.height, this.width, length);
	}

	@Override
	public double getVolume() {
		return height*width*length;
	}

	@Override
	public double getSurfaceArea() {
		return 2*length*width + 2*length*height + 2*height*width;
	}

	@Override
	public String toString() {
		return String.format("Cuboid [%.2f x %.2f x %.2f]", height, width, length);
	}
}
