import java.util.Scanner;

public class Main {

    public static Circle createCircle(Point p1, Point p2, double radius) {
        if(radius <= 0 ) {
            return null;
        }
        double angle = p1.angleTo(p2);
        Point midPt = p1.midPoint(p2);
        double mqLen = midPt.distanceFrom(p2);
        double mcLen = Math.sqrt(radius*radius - mqLen*mqLen);
        Point centre = midPt.moveTo2(angle, mcLen);
        if(p1.x==p2.x && p1.y==p2.y) {
            return null;
        } else if(mqLen > radius) {
            return null;
        } else {
            Circle c = Circle.getCircle(centre, radius);
            return c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point[] points = new Point[2];

        for(int i=0; i<2; i++) {
            // System.out.println("Enter coordinates for point: ");
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            points[i] = new Point(x, y);
        }
        
        // System.out.println("Enter radius: ");
        double r = sc.nextDouble();

        // System.out.println(p2.moveTo(Math.PI/2, 1.0));
        Circle c = Main.createCircle(points[0], points[1], r);
        System.out.println((c==null)?"No valid circle can be created":("Created: " + c));
        sc.close();
        // System.out.println(Main.createCircle(new Point(0, 0), new Point(1, 0), 1)); //circle of radius 1.0 centered at point (0.500, 0.866)
        // System.out.println(new Point(0.5, 0.0).moveTo(0, 0.866025));
    }
}