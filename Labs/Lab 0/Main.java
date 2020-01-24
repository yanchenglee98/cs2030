import java.util.Scanner;

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


class Circle {
    Point centre;
    double radius;

    private Circle(Point p, double radius) {
        this.radius = radius;
        this.centre = p;
    }

    public static void getCircle(Point p, double radius) {
        Circle c = new Circle(p, radius);
        System.out.printf("%nCircle of radius %.1f centered at point (%.3f, %.3f)%n", radius, p.x, p.y);
    }

    public static void createCircle(Point p1, Point p2, double radius) {
        double angle = p1.angleTo(p2);
        Point midPt = p1.midPoint(p2);
        double mqLen = midPt.distanceFrom(p1);
        double mcLen = Math.sqrt(radius*radius - mqLen*mqLen);
        Point centre = midPt.moveTo(angle, mcLen);
        if(p1.x==p2.x && p1.y==p2.y) {
            System.out.println("null");
            return;
        } else if(mqLen > radius) {
            System.out.println("null");
            return;
        } else {
            Circle c = new Circle(centre, radius);
            System.out.printf("%nCircle of radius %.1f centered at point (%.3f, %.3f)%n", radius, centre.x, centre.y);
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Point p1 = new Point(0, 0);
        // Point p2 = new Point(2.0, 0);
        // Point p3 = new Point(3, 0);
        Point[] points = new Point[2];

        for(int i=0; i<2; i++) {
            System.out.println("Enter coordinates for point: ");
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            points[i] = new Point(x, y);
        }
        
        System.out.println("Enter radius: ");
        double r = sc.nextDouble();

        // System.out.println(p2.moveTo(Math.PI/2, 1.0));
        Circle.createCircle(points[0], points[1], r);
        sc.close();
    }
}