import java.util.Comparator;

/**
 * Event time comparator.
 * Used to compare two events.
 * @author Lee Yan Cheng
 */

public class EventTimeComparator implements Comparator<Event> {
    
    /**
     * <p> Overridden compare method that compares event times, ID, State. </p>
     * @param e1 first event
     * @param e2 second event that will be compared to e1
     * @return returns 1 if e1 is larger than e2, 0 if same and -1 if smaller
     */
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getTime() > e2.getTime()) {
            return 1;
        } else if (e1.getTime() < e2.getTime()) {
            return -1;
        } else { // if time is same compare ID
            if (e1.getID() > e2.getID()) {
                return 1;
            } else if (e1.getID() < e2.getID()) {
                return -1;
            } else { // if ID is same compare states
                if (e1.getStateID() > e2.getStateID()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } 
    }
}