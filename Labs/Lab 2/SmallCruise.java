public class SmallCruise extends Cruise {
	private final static int loadTime = 30;
	private final static int loaderRequired = 1;

	SmallCruise(String ID, int timeOfArrival) {
		super(ID, timeOfArrival, loaderRequired, loadTime);
	}

}
