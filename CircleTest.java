class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point midPoint(Point p) {
        double x = (this.x + p.x) / 2;
        double y = (this.y + p.y) / 2;
        Point q = new Point(x, y);
        return q;
    }

    public double distance() {
        double dist = Math.sqrt(this.x*this.x + this.y* this.y);
        return dist;
    }

    public double distanceFrom(Point p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        double dist = Math.sqrt(dx*dx + dy*dy);
        return dist;
    }

    public double angleTo(Point p) {
        double dx = p.x - this.x;
        double dy = p.y - this.y;
        double angle = Math.atan2(dx, dy);
        return angle;
    }

    public Point moveTo(double angle, double d) {
        double x = this.x + d*Math.cos(angle);
        double y = this.y + d*Math.sin(angle);
        Point p = new Point(x, y);
        return p;
    }

    @Override
    public String toString() {
        return String.format("Point (%.3f, %.3f)", this.x, this.y);
    }
}

public class CircleTest {
    public static void main(String[] args) {
        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(0, 0);
        Point p3 = new Point(-1, -1);
        System.out.println(p2.moveTo(Math.PI/2, 1.0));
    }
}