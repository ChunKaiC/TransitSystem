package transitapp;

import java.util.ArrayList;

public class SubwayRoute extends TransitRoutes{

	protected ArrayList<Station> currRoute;

	public SubwayRoute(LocationNode node, double fare) {
		super(node, fare);
	}
	
    public static void main(String[] args) {

    }
}
