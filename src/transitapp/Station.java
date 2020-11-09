package transitapp;

import java.util.ArrayList;
//import java.util.Observable;
import java.util.Observer;

public class Station extends Location implements Observer{
	private static ArrayList<Station> ALL_STAITIONS = new ArrayList<Station>();

	public Station(String location, boolean atInjunction) {
		super(location, atInjunction);
		Station.ALL_STAITIONS.add(this);
	}

	@Override
	public ArrayList<Location> getAllDestinations() {
		// TODO Auto-generated method stub
		ArrayList<Location> all = this.getAllDestinations(this.getOnRoutes().get(0));
		return all;
	}
	
	private ArrayList<Location> getAllDestinations(TransitRoutes TransitRoute) {
		// iterate through transitroute and add all locations until the location is reached
		ArrayList<Location> leftDestinations = new ArrayList<Location>();
		for (Location l: TransitRoute.getRoute()) {
			if (!(l.getLocation().equals(this.getLocation()))) {
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
