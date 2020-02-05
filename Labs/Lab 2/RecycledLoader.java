public class RecycledLoader extends Loader.java {
    private final boolean recycled;
    private final int ServiceTime;

    RecycledLoader(int ID, boolean avail, Cruise serving, boolean recycled) {
        super(ID, avail, serving);
        this.ServiceTime = serving.getServiceCompletionTime() + 60;
        this.recycled = recycled     
    }

    @Override
    public boolean canServe(Cruise cruise) {
        if(avail) {
            return true;
        } else {
            if(cruise.getArrivalTime()-this.ServiceTime>=0) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public Loader serve(Cruise cruise) {
        if(cruise == null) {
            return new Loader(this.ID);
        } else if(this.avail || this.canServe(cruise)) {
            // avail = false;
            // serving = cruise;
            return new RecycledLoader(this.ID, false, cruise, true);
        } else {
            return null;
        }
    }

    @Override 
    public String toString() {
        if(avail) {
            return "Loader (Recycled) " + this.ID;
        } else {
            if(recycled) {
                return "Loader " + this.ID + " (recycled) serving " + serving.toString();
            } else {
                return "Loader " + this.ID + " serving " + serving.toString();
            }
        }
    }
}
