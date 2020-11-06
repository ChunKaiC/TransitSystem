package transitapp;

import java.util.ArrayList;

public class AdminUser {
	private static double totalRevenue;
	private static double totalCost;
	private static int numBuses;
	private static double busPrice;
	private static double busTravelCost; // the cost (expenses) for a bus to go from one stop to another.
	private static double subwayTravelCost; // the cost (expenses) for a subway to go from one station to another.
	// For simplicity, the travel expenses will be calculated using the 2 above ^
	protected static ArrayList<TransitRoutes> busRoutes;
	
	
	public AdminUser(int numBuses, double busPrice, double busTravelCost, double subwayTravelCost,
					 ArrayList<TransitRoutes> busRoutes) {
		totalRevenue = 0;
		totalCost = 0;
		AdminUser.busPrice = busPrice;
		AdminUser.numBuses = numBuses;
		AdminUser.subwayTravelCost = subwayTravelCost;
		AdminUser.busTravelCost = busTravelCost;
		AdminUser.busRoutes = busRoutes;
	}
	public double showDailyReport() {
		return 0.0;
		// TODO
	}
	public static void addRevenue(double rev) {
		totalRevenue += rev;
	}
	
	public static void purchaseBuses(int numBuses) {
		AdminUser.numBuses += numBuses;
		totalCost += numBuses * busPrice;
	}

	public static void createNewBusRoute(TransitRoutes route) {
		AdminUser.busRoutes.add(route);
	}

	public static void deleteNewBusRoute(TransitRoutes route) {
		AdminUser.busRoutes.remove(route);
	}

	public static void addLocation(Location loc, TransitRoutes route, int index) {
		route.addLocationToRoute(loc, index);
	}

	public static void removeLocation(Location loc, TransitRoutes route) {
		route.removeLocationFromRoute(loc);
	}
}
