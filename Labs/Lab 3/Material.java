public class Material {
	private String material;
	private double density;

	public Material(String material, double density) {
		this.material = material;
		this.density = density;
	}

	public String getMaterial() {
		return this.material;
	}

	public double getDensity() {
		return this.density;
	}

	@Override
	public String toString() {
		return "Material is: " + material + " Density: " + density;
	}
}
