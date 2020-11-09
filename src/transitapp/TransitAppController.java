package transitapp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TransitAppController {

	public static void main(String[] args) {
		/**
		ArrayList<Location> busStops = new ArrayList<Location>(Arrays.asList(new Stop("Toronto Plaza", false),  
				new Stop("Woodchester", false), new Stop("Port Credit", false), new Stop("Clarkson", true), 
				new Stop("Lorne Park", false), new Stop("Erin Mills", false)));
		
		ArrayList<Location> stations = new ArrayList<Location>(Arrays.asList(new Station("Clarkson", true), 
				new Station("Faisal Go", false), new Station("Nick Go", false), 
				new Station("Chung Chung Go", false),new Station("Pajdakov Go", false)));
		*/
		/**
		Scanner myObj = new Scanner(System.in);

	    System.out.println("Enter name");

	    // String input
	    String name = myObj.nextLine();

	    // Numerical input
	    //int age = myObj.nextInt();
	    //double salary = myObj.nextDouble();

	    // Output input by user
	    System.out.println("Name: " + name);
	    //System.out.println("Age: " + age);
	    //System.out.println("Salary: " + salary);
		*/
		// until nick implements loaders
		
		TransitAppController tac = new TransitAppController();
		tac.openTransitSystem();
	}
	
	//play method
	private void openTransitSystem() {
		// once loaders are made, load everything here. LOAD ONLY CARDHOLDERS AND EVENTS.TXT
		// until then
		CardHolder faisal = new CardHolder("Faisal", "faisalm.1724@gmail.com");
		Card f = new Card(25, 1);
		faisal.addCard(f);
		ArrayList<CardHolder> allUsers = new ArrayList<CardHolder>();
		allUsers.add(faisal);
		// essential code
		/**
		Scanner continueFactor = new Scanner(System.in);
		System.out.println("Please enter your name");
		String name = continueFactor.nextLine();
		*/
		//System.out.println(name);
		boolean continueApp = true;
		while (continueApp) {
			Scanner continueFactor = new Scanner(System.in);
			System.out.println("Please enter your name");
			String name = continueFactor.nextLine();
			System.out.println(name);
			
			
			// once loader implemented, look through cardholders and find the person that equals name and continue
			boolean found = false;
			CardHolder currUser = null;
			for (CardHolder u : allUsers) {
				// CHANGE TO CHECK EQUIVELLENT EMAIL NOT NAME, NAME IS NOT UNIQUE
				if (name.equals(u.getName())) {
					found = true;
					currUser = u;
				}
			}
			
			
			// either enter user account or make new account
			if (found) {
				System.out.println("Welcome " + currUser.getName());
				this.displayUser(currUser);
				break;
			}
			// if user not found, make new user and add to list of all users
			else {
				System.out.println("Name not found in directory of users");
				System.out.println("Would you like to make a new account? \n" + "Please indicate yes or no by inputing y or n");
				String yn = continueFactor.nextLine();
				if (yn.equals("y")) {
					System.out.println("Please enter your name");
					String newName = continueFactor.nextLine();
					System.out.println("Please enter your email");
					String newEmail = continueFactor.nextLine();
					boolean userExists = false;
					for (CardHolder u : allUsers) {
						if (newEmail.equals(u.getEmail())) {
							userExists = true;
						}
					}
					if (userExists) {
						System.out.println("The email entered already exists as a user");
						continue;
					}
					else {
						// here you will write to file and create new 
						currUser = new CardHolder(newName, newEmail);
						System.out.println("Welcome " + currUser.getName());
						
						// WRITE NEW USER TO FILE
						this.displayUser(currUser);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Displays users information and 
	 * @param currUser
	 */
	private void displayUser(CardHolder currUser) {
		System.out.println("Sup Bitch");
		// LOAD CARDS AND STOPS AND STATIONS AND ROUTES
		Stop stop1 = new Stop("McDowell", false);
		Stop stop1_5 = new Stop("WinstonChurchill", false);
		Stop stop2 = new Stop("GO StreetsVille", true);
		Station streets = new Station("GO StreetsVille", true);
		ArrayList<Location> route1 = new ArrayList<Location>();
		ArrayList<LocalTime> schedule = new ArrayList<LocalTime>();
		//LocalDateTime stop1time = new LocalDateTime(null, null)
		//schedule.add()
		route1.add(stop1);
		route1.add(stop1_5);
		route1.add(stop2);
		TransitRoutes route = new TransitRoutes("Go to StreetsVille", route1, schedule);
		// ESSENTIAL CODE
		while (true) {
			Scanner continueFactor = new Scanner(System.in);
			// BEGIN TRIP OR DISPLAY USER FUNCTIONS LIKE CHANGE NAME OR ADD BALANCE OR SUSPEND CARD ETC...
			System.out.println("Please input 'stop' to quit application, Input 'user' to see user functions"
					+ " or click enter to begin a trip");
			String stopornah = continueFactor.nextLine();
			if (stopornah.equals("stop")) {
				break;
			}
			if (stopornah.equals("user")) {
				this.userFunctions(currUser);
			}
			ArrayList<Location> allLocs = this.displayAllStarts();
			boolean validLocation = false;
			String startingLocation = "";
			Location start = null;
			while (!validLocation) {
				System.out.println("please chose a starting location from above");
				startingLocation = continueFactor.nextLine();
				for (Location l : allLocs) {
					if (l.toString().equals(startingLocation)) {
						start = l;
						validLocation = true;
						break;
					}
					
				}
				if (!validLocation) {
				System.out.println("Invalid Location");
				}
			}
			this.beginTrip(currUser, start);
		}
	}

	

	private void userFunctions(CardHolder currUser) {
		// TODO Auto-generated method stub
		Scanner continueFactor = new Scanner(System.in);
		boolean displayUserFunctions = true;
		System.out.println("Name: " + currUser.getName());
		System.out.println("Email: " + currUser.getEmail());
		System.out.println("Active Cards: " + currUser.getCards());
		System.out.println("Recent Trips: " + currUser.getRecentTrips());
		System.out.println("Average Monthly Spending: " + currUser.averageCost());

		while (displayUserFunctions) {
			System.out.println("Input From The Following List Your Desired Function. Please input '1' to '4'");
			System.out.println("1. Create a New Card");
			System.out.println("2. Suspend Active Card");
			System.out.println("3. Change Name");
			System.out.println("4. Make a trip");
			String function = continueFactor.nextLine();
			if (function.equals("1")) {
				this.createCard();
			}
			if (function.equals("2")) {
				this.suspendCard();
			}
			if (function.equals("3")) {
				this.changeName();
			}
			if (function.equals("4")) {
				break;
			} 
			System.out.println("\n" + "Would You Like To Commit Another User Function? Please Enter 'y' or 'n'");
			String runNext = continueFactor.nextLine();
			if (runNext.equals("n")) {
				displayUserFunctions = false;
			}
		}
	}

	private void changeName() {
		// TODO Auto-generated method stub
		// write to file and ask for input
	}

	private void suspendCard() {
		// TODO Auto-generated method stub
		// write to file and ask for input
	}

	private void createCard() {
		// TODO Auto-generated method stub
		// write to file and ask for input
	}

	private void beginTrip(CardHolder currUser, Location startingLocation) {
		// TODO Auto-generated method stub
		Scanner continueFactor = new Scanner(System.in);
		System.out.println("Current Location: " + startingLocation);
		Location currLocation = startingLocation;
		while (true) {
			System.out.println("Please Choose From The Following List of destinations" + "\n" + "If You Would Like To Choose a Different Start"
					+ " input 'Change Start'" + "\n" + "To End Trip, Input 'End'");
			ArrayList<Location> possibleDest = currLocation.getAllDestinations();
			System.out.println(possibleDest);
			String nextDestination = continueFactor.nextLine();
			if (nextDestination.equals("Change Start")) {
				break;
			}
			if (nextDestination.equals("End")) {
				break;
			}
			boolean found = false;
			for (Location l : possibleDest) {
				if (l.toString().equals(possibleDest)) {
					found = true;
				}
			}
			if (found) {
				// write event to file
				//currUser.tapOn(currLocation, card_id, route, time)
				// let user pick card id, then call bus trip or subway trip, for subway trip force tap off
				
			}
		}
	}

	private ArrayList<Location> displayAllStarts() {
		//System.out.println(Stop.getAllLocations());
		// FOR STATIONS, WE NEED TO BE ABLE TO SHOW STATIONS OR BUSSES, OR HAVE A DIFFERENCE IN NAMES LIKE "GO StreetsVille: STOP" OR
		// "GO StreetsVille: STATION"
		//System.out.println(Station.getAllLocations());
		ArrayList<Location> allLocs = Location.getAllLocations();
		System.out.println(allLocs);
		return allLocs;
	}
	
	

}
