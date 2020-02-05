public class Loader {
    protected final int ID;
    protected final boolean avail;
    protected final Cruise serving;

    Loader(int ID) {
        this.ID = ID;
	    this.avail = true;
        this.serving = null;
    }

    Loader(int ID, boolean avail) {
        this.ID = ID;
        this.avail = avail;
        this.serving = null;
    }

    Loader(int ID, boolean avail, Cruise serving) {
        this.ID = ID;
        this.avail = avail;
        this.serving = serving;
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
        } else if(this.avail || this.canServe(cruise)) {
           // avail = false;
           // serving = cruise;
            return new Loader(this.ID, false, cruise);
        } else {
            return null;
        }
    }


    @Override 
    public String toString() {
        if(avail) {
            return "Loader " + this.ID;
        } else {
                return "Loader " + this.ID + " serving " + serving.toString();
        }
     }
}
