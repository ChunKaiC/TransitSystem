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
	protected static ArrayList<BusRoute> busRoutes;
	
	
	public AdminUser(int numBuses, double busPrice, double busTravelCost, double subwayTravelCost,
					 ArrayList<BusRoute> busRoutes) {
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

	public static void createNewBusRoute(BusRoute route) {
		AdminUser.busRoutes.add(route);
	}

	public static void addStop(Stop stop, BusRoute route, int index) {
		route.currRoute.add(index, stop);
	}

	public static void removeStop(Stop stop, BusRoute route) {
		route.currRoute.remove(stop);
	}

	public static void addStation(Station station, SubwayRoute route, int index) {
		route.currRoute.add(index, station);
	}

	public static void removeStation(Station station, SubwayRoute route) {
		route.currRoute.remove(station);
	}
}
