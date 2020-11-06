package transitapp;

import java.util.ArrayList;
//import java.util.Observable;
import java.util.Observer;

public class Stop extends Location implements Observer {

	private static ArrayList<Stop> ALL_STOPS;
	
	public Stop(String location, boolean atInjunction) {
		super(location, atInjunction);
		ALL_STOPS.add(this);
	}
	
	@Override
	public ArrayList<Location> getAllDestinations(Location stop) {
		// add return empty array if onroutes empty
		int i = 0;
		ArrayList<Location> onRoutDestinations = new ArrayList<Location>();
		while (i < this.getOnRoutes().size()) {
			TransitRoutes route = this.getOnRoutes().get(i);
			onRoutDestinations.addAll(getRouteDestinations(route, stop));
			i++;
		}
		return onRoutDestinations;
		
	}
	
	private ArrayList<Location> getRouteDestinations(TransitRoutes route, Location stop) {
		ArrayList<Location> allDestinations = new ArrayList<Location>();
		// iterate through the TransiteRoute given and add all stops after given stop to all destinations and return
		//this.getOnRoutes();
		for (TransitRoutes r : this.getOnRoutes()) {
			boolean found = false;
			for (Location l : r.getRoute()) {
				if (found) {
					allDestinations.add(l);
				}
				else {
					if (l.getLocation().equals(stop.getLocation())) {
						found = true;
					}
				}
			}
		}
		return allDestinations;
		
	}
	
	public ArrayList<Stop> getAllStops() {
		return Stop.ALL_STOPS;
	}
	
}
