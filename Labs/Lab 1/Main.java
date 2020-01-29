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
        Point centre = midPt.moveTo(angle+(Math.PI/2), mcLen);
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

       int numberOfPoints = sc.nextInt();
       Point[] points = new Point[numberOfPoints];
       for(int i=0; i<numberOfPoints; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            points[i] = new Point(x,y);
       }
       int largest = 0;
       for(int i=0; i<numberOfPoints-1; i++) {
            for(int j=i+1; j<numberOfPoints; j++) {
                if(points[i].distanceFrom(points[j])<=2) {
                    Circle c1 = Main.createCircle(points[i], points[j], 1);
                    Circle c2 = Main.createCircle(points[j], points[i], 1);
                    int x;
                    if(c1.coverage(points) > c2.coverage(points)) {
                        x = c1.coverage(points);
                    } else {
                        x = c2.coverage(points);
                    }
                    if(x>largest) {
                        largest=x;
                    }
                }
            }
        }
        System.out.println("Maximum Disc Coverage: " + largest);
        sc.close();
        // System.out.println(Main.createCircle(new Point(0,1), new Point(0,0), 1));
    }
}
