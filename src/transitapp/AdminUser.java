package transitapp;

public class AdminUser {
	private static double totalRevenue;
	private static double totalCost;
	private static int numBuses;
	private static double busPrice;
	// How much does it cost to maintain the busses / subways? How will we keep track of it?
	
	
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
}
