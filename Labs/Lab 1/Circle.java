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

    public int coverage(Point[] arr) {  
        int counter = 0;
        for(Point i: arr) {
            if(this.centre.distanceFrom(i)<=this.radius) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        return String.format("circle of radius %.1f centered at point (%.3f, %.3f)", radius, centre.x, centre.y);
    }
}
