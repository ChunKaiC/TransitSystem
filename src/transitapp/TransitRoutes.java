package transitapp;

import java.util.ArrayList;

public abstract class TransitRoutes {
	private String name;
	private ArrayList<Location> route;
	private double fare;
	
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
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
    public static void main(String[] args) {

    }
}
