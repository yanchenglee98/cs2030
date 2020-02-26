public class BigCruise extends Cruise {
	private final int length;
	private final int numberOfPassengers;
	private final static int loadingRate = 50;
	private final static int lengthPerLoader = 40;

	BigCruise(String ID, int timeOfArrival, int length, int numberOfPassengers) {
		super(ID, timeOfArrival, (int)Math.ceil((double)length/lengthPerLoader), (int)Math.ceil((double)numberOfPassengers/loadingRate));
		this.length = length;
		this.numberOfPassengers = numberOfPassengers;	
	}

}
