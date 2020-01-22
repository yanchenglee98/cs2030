public class Circle {
	private Point centre;
	private double radius;

	Circle(Point centre) {
		this.centre = centre;
		this.radius = 1.0;
	}

	Circle(Point centre, double radius) {
		this.centre = centre;
		this.radius = radius;
	}

	boolean contains(Point point) {
		return centre.distance(point) <= radius;
	}

	static void findCoverage(Point[] points) {
		for(Point i: points) {
			Circle c = new Circle(i);
			int sum = 0;
			for(Point j: points) {
				if(c.contains(j)) {
					sum++;
				}
			}
			System.out.println(c + " contains " + sum + " points.");
		}
	}

	@Override
	public String toString() {
		return "Circle centered at " + this.centre + " with radius " + this.radius;
	}
}
