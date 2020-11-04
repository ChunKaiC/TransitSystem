package transitapp;

import java.util.ArrayList;

public class TransitRoutes {
	private ArrayList<Object> route;
	private double fare;
	
	public TransitRoutes(ArrayList<Object> route, double fare) {
		this.route = route;
		this.fare = fare;
	}

	public double getFare() {
		return this.fare;
	}

	
    public static void main(String[] args) {

    }
}
