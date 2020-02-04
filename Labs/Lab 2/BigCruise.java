public class BigCruise extends Cruise {
	private final int length;
	private final int numberOfPassengers;

	BigCruise(String ID, int timeOfArrival, int length, int numberOfPassengers) {
		super(ID, timeOfArrival, (int)Math.ceil((double)length/40), (int)Math.ceil((double)numberOfPassengers/50));
		this.length = length;
		this.numberOfPassengers = numberOfPassengers;	
	}

}
