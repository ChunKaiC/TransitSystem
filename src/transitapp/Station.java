package transitapp;

import java.util.ArrayList;
//import java.util.Observable;
import java.util.Observer;

public class Station extends Location implements Observer{
	private static ArrayList<Station> ALL_STAITIONS;

	public Station(String location, boolean atInjunction) {
		super(location, atInjunction);
		ALL_STAITIONS.add(this);
	}

	@Override
	public ArrayList<Location> getAllDestinations(Location location) {
		// TODO Auto-generated method stub
		ArrayList<Location> all = this.getAllDestinations(location, this.getOnRoutes().get(0));
		return all;
	}
	
	private ArrayList<Location> getAllDestinations(Location location, TransitRoutes TransitRoute) {
		// iterate through transitroute and add all locations until the location is reached
		ArrayList<Location> leftDestinations = new ArrayList<Location>();
		for (Location l: TransitRoute.getRoute()) {
			if (!(l.getLocation().equals(location.getLocation()))) {
				leftDestinations.add(l);
			}
		}
		return leftDestinations;
	}
	

	
	public ArrayList<Station> getAllStations() {
		return Station.ALL_STAITIONS;
	}
	
	public String toString() {
		return ("Subway Station: " + this.getLocation());
	}

}
