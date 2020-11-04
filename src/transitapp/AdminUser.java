package transitapp;

public class AdminUser {
	private static double totalRevenue;
	private static double totalCost;
	private static int numBuses;
	private static double busPrice;
	private static double busTravelCost; // the cost (expenses) for a bus to go from one stop to another.
	private static double subwayTravelCost; // the cost (expenses) for a subway to go from one station to another.
	// For simplicity, the travel expenses will be calculated using the 2 above ^
	
	
	public AdminUser(double totalRev, double totalCost, int numBuses, double busPrice) {
		totalRevenue = 0;
		totalCost = 0;
		AdminUser.busPrice = busPrice;
		AdminUser.numBuses = numBuses;
	}
	public double showDailyReport() {
		return 0.0;
	}
	public static void addRevenue(double rev) {
		totalRevenue += rev;
	}
	
	public static void purchaseBuses(int numBuses) {
		AdminUser.numBuses += numBuses;
		totalCost += numBuses * busPrice;
	}

	public static void addStop(Stop stop, BusRoute route, int index) {
		// add a stop to the bus route
	}

	public static void removeStop(Stop stop, BusRoute route, int index) {
		// remove a stop from the bus route
	}

	public static void addStation(Station station, int index) {
		// add a station to the subway route
	}

	public static void removeStation(Station station, int index) {
		// remove station from the subway
	}
}
