public class Loader {
    private final int ID;
    private final boolean avail;
    private final Cruise serving;
    private final boolean recycled;

    Loader(int ID) {
        this.ID = ID;
	    this.avail = true;
        this.serving = null;
        this.recycled = false;
    }

    Loader(int ID, boolean avail, Cruise serving) {
        this.ID = ID;
        this.avail = avail;
        this.serving = serving;
        this.recycled = false;
    }

    Loader(int ID, boolean recycled) {
        this.ID = ID;
        this.avail = true;
        this.serving = null;
        this.recycled = recycled;
    }

    Loader(int ID, boolean avail, Cruise serving, boolean recycled) {
        this.ID = ID;
        this.avail = avail;
        this.serving = serving;
        this.recycled = recycled;
    }

    public boolean canServe(Cruise cruise) {
        if(avail) {
            return true;
        } else {
            if(cruise.getArrivalTime()-serving.getServiceCompletionTime()>=0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Loader serve(Cruise cruise) {
        if(cruise == null) {
            return new Loader(this.ID);
        } else if(this.recycled) {
            return new Loader(this.ID, false, cruise, true);
        } else if(this.avail || this.canServe(cruise)) {
           // avail = false;
           // serving = cruise;
            return new Loader(this.ID, false, cruise);
        } else {
            return null;
        }
    }

    /**
     * @return the recycled
     */
    public boolean isRecycled() {
        return recycled;
    }

    @Override 
    public String toString() {
        if(avail) {
            return "Loader " + this.ID;
        } else {
            if(recycled) {
                return "Loader " + this.ID + " (recycled) serving " + serving.toString();
            } else {
                return "Loader " + this.ID + " serving " + serving.toString();
            }
        }
    }
}
