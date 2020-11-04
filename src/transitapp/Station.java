package transitapp;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Station extends Location implements Observer{
	private static ArrayList<Station> ALL_STAITIONS;

	public Station(String location, boolean atInjunction) {
		super(location, atInjunction);
		ALL_STAITIONS.add(this);
	}

	@Override
	public void update(Observable o, Object TransitRoute) {
		// TODO Auto-generated method stub
		this.addOnRoute((TransitRoutes) TransitRoute);
	}

	@Override
	public ArrayList<Location> getAllDestinations(Location location) {
		// TODO Auto-generated method stub
		ArrayList<Location> left = this.getLeft(location, this.getOnRoutes().get(0));
		ArrayList<Location> right = this.getRight(location, this.getOnRoutes().get(0));
		left.addAll(right);
		return left;
	}
	
	private ArrayList<Location> getLeft(Location location, TransitRoutes TransitRoute) {
		// iterate through transitroute and add all locations until the location is reached
		return null;
	}
	
	private ArrayList<Location> getRight(Location location, TransitRoutes TransitRoute) {
		// iterate through transitroute and add all locations after the given location
		return null;
	}
	
	public ArrayList<Station> getAllStations() {
		return Station.ALL_STAITIONS;
	}

}
