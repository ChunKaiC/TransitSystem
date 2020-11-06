package transitapp;

import java.util.ArrayList;
import java.util.Observable;

public class TransitRoutes extends Observable{
	private String name;
	private ArrayList<Location> route;
	static double fare;
	
	public TransitRoutes(String name, ArrayList<Location> route, double fare) {
		this.name = name;
		this.route = route;
		this.fare = fare;
	}

	public double getFare() {
		return this.fare;
	}
	
	public void setFare(double newFare) {
		this.fare = newFare;
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
	
	public void addLocation() {
		
	}
	
	public void removeLocation(String name) {
		for (int i = 0; i < this.route.size(); i++) {
			if (this.route.get(i).getLocation() == name) {
				this.route.remove(i);
				return;
			}
		}
	}
	
	public void extendRoute() {
		
	}
	
    public static void main(String[] args) {

    }
}
