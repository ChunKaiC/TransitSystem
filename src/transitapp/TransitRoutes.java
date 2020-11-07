package transitapp;

import java.util.ArrayList;
import java.util.Observable;

public class TransitRoutes extends Observable{
	private String name;
	private ArrayList<Location> route;
	private ArrayList<Integer> travelTime;
	static double fare;
	
	public TransitRoutes(String name, ArrayList<Location> route, double fare) {
		this.name = name;
		this.route = route;
		this.setFare(fare);
	}
	
	public void addLocationToRoute(Location loc, int index) {
		this.route.add(index, loc);
		this.addObserver(loc);
		this.notifyObservers(this);
	}

	public void removeLocationFromRoute(Location loc) {
		this.route.remove(loc);
		this.deleteObserver(loc);
		loc.getOnRoutes().remove(this);
	}

	public double getFare() {
		return fare;
	}
	
	public void setFare(double newFare) {
		fare = newFare;
	}
	
	public ArrayList<Location> getRoute(){
		return this.route;
	}
	
	public void setRoute(ArrayList<Location> newRoute){
		this.route = newRoute;
		for (Location l : this.route) {
			this.addObserver(l);
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
    public static void main(String[] args) {
    	System.out.println("Hello World");
    }
}
