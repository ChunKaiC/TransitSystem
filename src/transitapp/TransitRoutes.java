package transitapp;

import java.util.ArrayList;
import java.util.Observable;
import java.time.LocalTime;

public class TransitRoutes extends Observable{
	private String name;
	private ArrayList<Location> route;
	private ArrayList<LocalTime> schedule; // parallel to routes above
	static double busFare, subwayFare;
	
	public TransitRoutes(String name, ArrayList<Location> route, ArrayList<LocalTime> schedule) {
		this.name = name;
		this.route = route;
		this.schedule = schedule;
		//this.setFare(fare);
		for (Location l : this.route) {
			this.addObserver(l);
		}
		this.setChanged();
		this.notifyObservers(this);
		TransitRoutes.setBusFare(busFare);
		TransitRoutes.setSubwayFare(subwayFare);
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

	public static double getBusFare() {
		return busFare;
	}

	public static double getSubwayFare() {
		return subwayFare;
	}
	
	public static void setBusFare(double newFare) {
		busFare = newFare;
	}

	public static void setSubwayFare(double newFare) {
		subwayFare = newFare;
	}
	
	public ArrayList<Location> getRoute(){
		return this.route;
	}
	
	public ArrayList<LocalTime> getSchedule(){
		return this.schedule;
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
