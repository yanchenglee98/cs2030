public class SolidShape3D {
	protected final Shape3D shape;
	protected final Material material;

	public SolidShape3D(Shape3D shape, Material material) {
		this.shape = shape;
		this.material = material;
	}
	
	public double getMass() {
		return material.getDensity() * shape.getVolume();
	}

	public double getDensity() {
		return material.getDensity();
	}

	@Override
	public String toString() {
		return "Solid" + shape.toString() + String.format(" with a mass of %.2f",  getMass());
	}
}
