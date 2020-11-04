package transitapp;

import java.util.ArrayList;

public class BusRoute extends TransitRoutes {

	private ArrayList<Stop> currRoute;

	public BusRoute(LocationNode node, double fare) {
		super(node, fare);
	}
	
    public static void main(String[] args) {

    }
}
