package transitapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

		
		
public class StartUp {
		
		public static HashMap<String, Stop> stops;
		public static HashMap<String, Station> stations;
		public static ArrayList<TransitRoutes> busRoutes;
		public static ArrayList<TransitRoutes> subwayRoutes;
		public static HashMap<String, CardHolder> cardHolders;
		public static HashMap<String, ArrayList<Card>> cards;
		
		
		public static void main() throws FileNotFoundException {
		HashMap<String, Stop> stops = loadStops();
		HashMap<String, Station> stations = loadStation();
		ArrayList<TransitRoutes> busRoutes = loadBusRoutes();
		ArrayList<TransitRoutes> subwayRoutes = loadSubwayRoute();
		HashMap<String, CardHolder> cardHolders= loadCardHolders();
		HashMap<String, ArrayList<Card>> cards = loadCards();
		
		loadSettings();
		loadEvents(cardHolders, stops, stations);
	}

	/**
	 * Load the bus stops from the file
	 * @return a HashMap of the stops, keyed by name
	 * @throws FileNotFoundException
	 */
	public static HashMap<String, Stop> loadStops() throws FileNotFoundException{
		HashMap<String, Stop> stops = new HashMap<String, Stop>();
		BufferedReader fileStops = new BufferedReader(new FileReader("Resources/BusStops.txt"));
		Scanner scanStops = new Scanner(fileStops);

		while(scanStops.hasNextLine()) {
			String line = scanStops.nextLine();
			String[] data = line.split(",");
			stops.put(data[0], new Stop(data[0], Boolean.parseBoolean(data[1])));
		}
		scanStops.close();
		return stops;
	}

	/**
	 * Load the subway stations from the file
	 * @return a HashMap of the stations, keyed by name
	 * @throws FileNotFoundException
	 */
	public static HashMap<String, Station> loadStation() throws FileNotFoundException{
		HashMap<String, Station> stations = new HashMap<String, Station>();
		BufferedReader fileStations = new BufferedReader(new FileReader("Resources/Stations.txt"));
		Scanner scanStations = new Scanner(fileStations);

		while(scanStations.hasNextLine()){
			String line = scanStations.nextLine();
			String[] data = line.split(",");
			stations.put(data[0], new Station(data[0], Boolean.parseBoolean(data[1])));
		}

		scanStations.close();
		return stations;
	}

	/**
	 * Load the bus routes from the file
	 * @return an ArrayList with the bus routes
	 * @throws FileNotFoundException
	 */
	public static ArrayList<TransitRoutes> loadBusRoutes() throws FileNotFoundException{
		ArrayList<TransitRoutes> busRoutes = new ArrayList<TransitRoutes>();
		BufferedReader fileBusRoutes = new BufferedReader(new FileReader("Resources/BusRoutes.txt"));
		Scanner scanBusRoutes = new Scanner(fileBusRoutes);
		HashMap<String, Stop> stops = loadStops();

		while(scanBusRoutes.hasNextLine()) {
			String line = scanBusRoutes.nextLine();
			ArrayList<String> data = new ArrayList<String>(Arrays.asList(line.split(",")));
			ArrayList<Location> destinations = new ArrayList<Location>();
			ArrayList<LocalTime> schedule = new ArrayList<LocalTime>();

			for(int i=1; i < data.size(); i+=3) {
				destinations.add(stops.get(data.get(i)));
				int hour = Integer.parseInt(data.get(i+1));
				int min = Integer.parseInt(data.get(i+2));
				schedule.add(LocalTime.of(hour, min));
			}
			TransitRoutes  r = new TransitRoutes(data.get(0), destinations, schedule);
			busRoutes.add(r);
			System.out.println(r.countObservers());
		}
		scanBusRoutes.close();
		return busRoutes;
	}

	/**
	 * Load the subway route from the file
	 * @return An ArrayList containing (only) the subway route
	 * @throws FileNotFoundException
	 */
	public static ArrayList<TransitRoutes> loadSubwayRoute() throws FileNotFoundException{
		ArrayList<TransitRoutes> subwayRoutes = new ArrayList<TransitRoutes>();
		BufferedReader fileBusRoutes = new BufferedReader(new FileReader("Resources/StationRoutes.txt"));
		Scanner scanStationRoutes = new Scanner(fileBusRoutes);
		HashMap<String, Station> stops = loadStation();

		while(scanStationRoutes.hasNextLine()) {
			String line = scanStationRoutes.nextLine();
			ArrayList<String> data = new ArrayList<String>(Arrays.asList(line.split(",")));
			ArrayList<Location> destinations = new ArrayList<Location>();
			ArrayList<LocalTime> schedule = new ArrayList<LocalTime>();

			for(int i=1; i < data.size(); i+=3) {
				destinations.add(stops.get(data.get(i)));
				int hour = Integer.parseInt(data.get(i+1));
				int min = Integer.parseInt(data.get(i+2));
				schedule.add(LocalTime.of(hour, min));
			}
			subwayRoutes.add(new TransitRoutes(data.get(0), destinations, schedule));
		}
		scanStationRoutes.close();
		return subwayRoutes;
	}

	/**
	 * Load the card holders from the file
	 * @return A HashMap of CardHolders, keyed by name
	 * @throws FileNotFoundException
	 */
	public static HashMap<String, CardHolder> loadCardHolders() throws FileNotFoundException{
		loadSettings();
		HashMap<String, CardHolder> cardHolders = new HashMap<String, CardHolder>();
		HashMap<String, ArrayList<Card>> updatedCards = loadCards();
		BufferedReader fileCardHolders = new BufferedReader(new FileReader("Resources/CardHolders.txt"));
		Scanner scanCardHolders = new Scanner(fileCardHolders);
		
		while(scanCardHolders.hasNextLine()) {
			String line = scanCardHolders.nextLine();
			String[] data = line.split(",");
			cardHolders.put(data[1], new CardHolder(data[0], data[1]));
		}

		for(String key: updatedCards.keySet()) {
			cardHolders.get(key).deleteAllCards();
			for (int i = 0; i < updatedCards.get(key).size(); i++) {
				cardHolders.get(key).addCard(updatedCards.get(key).get(i));
			}
		}
		
		scanCardHolders.close();
		return cardHolders;
	}


	/**
	 * Load the cards from the file
	 * @return A HashMap of the cards
	 * @throws FileNotFoundException
	 */
	public static HashMap<String, ArrayList<Card>> loadCards() throws FileNotFoundException{
		HashMap<String, ArrayList<Card>> cards = new HashMap<String, ArrayList<Card>>();
		BufferedReader fileCards = new BufferedReader(new FileReader("Resources/Cards.txt"));
		Scanner scanCards = new Scanner(fileCards);

		while(scanCards.hasNextLine()) {
			String line = scanCards.nextLine();
			String[] data = line.split(",");
			Card c = new Card(Double.parseDouble(data[1]), Integer.parseInt(data[2]));
			if(data[3].equals("true")) {
				c.activate();
			}
			else {
				c.desactivate();
			}
			
			if(cards.containsKey(data[0])) {
				cards.get(data[0]).add(c);
			}
			else {
				cards.put(data[0], new ArrayList<Card>(Arrays.asList(c)));
			}
		}

		scanCards.close();
		return cards;

	}

	/**
	 * Load the events that have already been done
	 * @param cardHolders A HashMap of the existing card holders
	 * @param stops A HashMap of the existing bus stops
	 * @param stations A HashMap of the existing subway stations
	 * @throws FileNotFoundException
	 */
	public static void loadEvents(HashMap<String, CardHolder> cardHolders,
			HashMap<String, Stop> stops, HashMap<String, Station> stations) throws FileNotFoundException{
		
		BufferedReader fileEvents = new BufferedReader(new FileReader("Resources/events.txt"));
		Scanner scanEvents = new Scanner(fileEvents);
		
		while(scanEvents.hasNextLine()) {
			String line = scanEvents.nextLine();
			ArrayList<String> data = new ArrayList<String>(Arrays.asList(line.split(",")));
			//Location location = new Stop(null, (Boolean) null);
			Location location;
			
			
			if (data.get(1).charAt(0) == '!') {
				location = stations.get(data.get(1).substring(1));
			} else {
				location = stops.get(data.get(1).substring(1));
			}
			
			CardHolder cardHolder = cardHolders.get(data.get(8));
			int cardID = Integer.parseInt(data.get(2));
				
			LocalDateTime time = LocalDateTime.of(Integer.parseInt(data.get(3)), 
					Integer.parseInt(data.get(4)), Integer.parseInt(data.get(5)), 
					Integer.parseInt(data.get(6)), Integer.parseInt(data.get(7)));
			System.out.println(data.get(0));
			if (data.get(0).equals("tapOn")) {
				cardHolder.tapOn(location, cardID, time, true);
			} else {
				cardHolder.tapOff((Station) location, cardID, time, true);
			}
				
		}
		scanEvents.close();

	}

	/**
	 * Load the parameters containing the BusFare, StationFare, Minute Grace Period and Max Cost
	 * @throws FileNotFoundException
	 */
	public static void loadSettings() throws FileNotFoundException{
		BufferedReader fileSettings = new BufferedReader(new FileReader("Resources/Settings.txt"));
		Scanner scanSettings = new Scanner(fileSettings);
		
		while(scanSettings.hasNextLine()) {
			String line = scanSettings.nextLine();
			String[] data = line.split(":");
			if(data[0].equals("BusFare")) {
				TransitRoutes.setBusFare(Double.parseDouble(data[1]));
			}
			else if(data[0].equals("StationFare")) {
				TransitRoutes.setSubwayFare(Double.parseDouble(data[1]));
			}
			else if(data[0].equals("Minute Grace Period")) {
				Trip.MINUTE_GRACE_PERIOD = Integer.parseInt(data[1]);
			}
			else if(data[0].equals("Minute Grace Period")) {
				Trip.MAX_COST = Double.parseDouble(data[1]);
			}
			
			
		}
		scanSettings.close();
	}
	
}
