package transitapp;

public class AdminUser {
	private static double totalRevenue;
	private static double totalCost;
	private static int numBuses;
	
	
	public AdminUser(double totalRev, double totalCost, int numBuses) {
		totalRevenue = 0;
		totalCost = 0;
		AdminUser.numBuses = numBuses;
	}
	public double showDailyReport() {
		return 0.0;
	}
	public static void addRevenue(double rev) {
		totalRevenue += rev;
	}
	
	public static void purchaseBuses(int numBuses, double cost) {
		numBuses += numBuses;
		totalCost += cost;
	}
}
