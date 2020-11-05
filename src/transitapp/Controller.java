package transitapp;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

	public static void main(String[] args) {
		ArrayList<Location> busStops = new ArrayList<Location>(Arrays.asList(new Stop("Toronto Plaza", false),  
				new Stop("Woodchester", false), new Stop("Port Credit", false), new Stop("Clarkson", true), 
				new Stop("Lorne Park", false), new Stop("Erin Mills", false)));
		
		ArrayList<Location> stations = new ArrayList<Location>(Arrays.asList(new Station("Clarkson", true), 
				new Station("Faisal Go", false), new Station("Nick Go", false), 
				new Station("Chung Chung Go", false),new Station("Pajdakov Go", false)));
		
		

	}

}
