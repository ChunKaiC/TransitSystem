package transitapp;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.DecimalFormat;

public class AdminUser {
	private static String transitName;
	private static double totalRevenue;
	private static double totalCost;
	private static int numBuses;
	private static double busPrice;
	private static double busTravelCost; // the cost (expenses) for a bus to go from one stop to another.
	private static double subwayTravelCost; // the cost (expenses) for a subway to go from one station to another.
	// For simplicity, the travel expenses will be calculated using the 2 above ^
	protected static ArrayList<TransitRoutes> busRoutes;
	
	public AdminUser(int numBuses, double busPrice, double busTravelCost, double subwayTravelCost,
					 ArrayList<TransitRoutes> busRoutes, String transitName) {
		totalRevenue = 0;
		totalCost = 0;
		AdminUser.busPrice = busPrice;
		AdminUser.numBuses = numBuses;
		AdminUser.subwayTravelCost = subwayTravelCost;
		AdminUser.busTravelCost = busTravelCost;
		AdminUser.busRoutes = busRoutes;
		AdminUser.transitName = transitName;
	}

	public String getTransitName() {
		return AdminUser.transitName;
	}

	public void setTransitName(String name) {
		AdminUser.transitName = name;
	}

	public void showDailyReport(LocalDate date) throws FileNotFoundException {
		HashMap<String, CardHolder> cardHolders = StartUp.loadCardHolders();
		ArrayList<TransitRoutes> busRoutes = StartUp.loadBusRoutes();
		ArrayList<TransitRoutes> subwayRoutes = StartUp.loadSubwayRoute();
		int rides = 0;
		double revenue = 0.0;
		DecimalFormat df2 = new DecimalFormat("0.00");

		for (String customer : cardHolders.keySet()) {
			for (Trip trip : cardHolders.get(customer).getTrips()) {
				if (trip.getStartTime().toLocalDate().equals(date)) {
					revenue += trip.getMoneySpentOnTrip();
					rides ++;
				}
			}
		}

		System.out.println("Report " + getTransitName() + "'s summary for " + date + ":");
		System.out.println("Number of rides: " + rides);
		System.out.println("Total revenue: $" + df2.format(revenue));
		System.out.println("\nBus Routes: (Fare : " +  df2.format(TransitRoutes.getBusFare()) + ")");

		for (TransitRoutes busRoute : busRoutes) {
			System.out.println("Route: " + busRoute.getName());
		}
		System.out.println("\nBus Routes: (Fare : " +  df2.format(TransitRoutes.getSubwayFare()) + ")");
		for (TransitRoutes subRoute : subwayRoutes) {
			System.out.println("Route: " + subRoute.getName());
		}

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
