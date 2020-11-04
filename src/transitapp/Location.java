package transitapp;

import java.util.ArrayList;
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
	private static ArrayList<Location> ALL_LOCATIONS;
	
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
	
	public ArrayList<Location> getAllLocations() {
		return Location.ALL_LOCATIONS;
	}
	
	public void addOnRoute(TransitRoutes TransitRoute) {
		this.onRoutes.add(TransitRoute);
	}
}
