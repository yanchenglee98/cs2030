public class Circle {
    Point centre;
    double radius;

    private Circle(Point p, double radius) {
        this.radius = radius;
        this.centre = p;
    }

    public static Circle getCircle(Point p, double radius) {
        if(radius <= 0) {
            return null;
        }
        Circle c = new Circle(p, radius);
        return c;
    }

    @Override
    public String toString() {
        return String.format("circle of radius %.1f centered at point (%.3f, %.3f)", radius, centre.x, centre.y);
    }

}
