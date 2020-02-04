import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numberOfCruises = sc.nextInt();
		Cruise[] cruise = new Cruise[numberOfCruises];
		List<Loader> loaders = new ArrayList<Loader>();
		int counter = 0;

		for(int i=0; i<numberOfCruises;i++) {
			String cruiseID = sc.next();
			int timeOfArrival = sc.nextInt();

			if(cruiseID.charAt(0)=='B') {
				int length = sc.nextInt();
				int numberOfPassengers = sc.nextInt();
				cruise[i] = new BigCruise(cruiseID, timeOfArrival, length, numberOfPassengers); 
			} else {
				cruise[i] = new SmallCruise(cruiseID, timeOfArrival);
			}
		}
		
		if(numberOfCruises>0) { // add first loader if there are cruises
			counter++;
			loaders.add(new Loader(counter));
		}
			
		for(Cruise cr: cruise) {
			for(int i=0; i<cr.getNumOfLoadersRequired(); i++) {
				boolean isLoaded = false;
				for(int j=0; j<loaders.size();j++) {
					if(loaders.get(j).canServe(cr)) { // if loader is avail to serve
						System.out.println(loaders.get(j).serve(cr));
						loaders.set(j, loaders.get(j).serve(cr)); // replace avail loader with used loader
						isLoaded = true;
						break;
					}
				}
				// if insufficient loaders
				if(!isLoaded) {
					counter++;
					loaders.add(new Loader(counter).serve(cr));
					System.out.println(new Loader(counter).serve(cr));
				}
			}
		}
	}
}
