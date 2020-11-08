package transitapp;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class Location implements Observer{
	/**
	 * atInjuction
	 * location
	 * onRoutes
	 * ALL_LOCATIONS
	 */

	private boolean atInjunction;
	private String location;
	private ArrayList<TransitRoutes> onRoutes;
	private static ArrayList<Location> ALL_LOCATIONS = new ArrayList<Location>();
	
	public Location(String location, boolean atInjunction) {
		this.atInjunction = atInjunction;
		this.location = location;
		this.onRoutes = new ArrayList<TransitRoutes>();
		Location.ALL_LOCATIONS.add(this);
	}
	
	public abstract ArrayList<Location> getAllDestinations(Location location);
	
	// Getters and setters
	public boolean getAtInjuction() {
		return this.atInjunction;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public ArrayList<TransitRoutes> getOnRoutes() {
		return this.onRoutes;
	}
	
	public static ArrayList<Location> getAllLocations() {
		return Location.ALL_LOCATIONS;
	}
	
	public void addOnRoute(TransitRoutes TransitRoute) {
		this.onRoutes.add(TransitRoute);
	}
	
	@Override
	public void update(Observable arg0, Object TransitRoute) {
		// TODO Auto-generated method stub
		boolean found = false;
		TransitRoutes route = (TransitRoutes) TransitRoute;
		for (TransitRoutes r : this.getOnRoutes()) {
			if (r.getName().equals(route.getName())) {
				found = true;
			}
		}
		if (! found) {
			this.addOnRoute(route);
		}
	}
}
