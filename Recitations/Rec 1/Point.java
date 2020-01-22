public class Point {
	private double x;
	private double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	double distance(Point otherpoint) {
		double dx = this.x - otherpoint.x;
		double dy = this.y - otherpoint.y;
		return Math.sqrt(dx*dx + dy*dy);
	}

	@Override 
	public String toString() {
		return String.format("(%.1f, %.1f)", this.x, this.y);
	}
}
