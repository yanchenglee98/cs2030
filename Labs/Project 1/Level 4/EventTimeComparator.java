import java.util.Comparator;

public class EventTimeComparator implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getTime() > e2.getTime()) {
            return 1;
        } else if (e1.getTime() < e2.getTime()) {
            return -1;
        } else {
            if (e1.getID() < e2.getID()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}