package transitapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class StartUp {
		public static void main(String[] args) throws FileNotFoundException {
//		HashMap<String, Stop> stops = loadStops();
//		HashMap<String, Station> stations = loadStation();
//		System.out.println(stops.get("Chung Chung Go"));
//		System.out.println(stations.get("Chung Chung Go"));
//		ArrayList<TransitRoutes> busRoutes = loadBusRoutes();
//		ArrayList<TransitRoutes> subwayRoutes = loadSubwayRoute();
//		HashMap<String, CardHolder> cardHolders = loadCardHolders();
//
//
//
//		for(int i = 0; i < busRoutes.size(); i++) {
//			System.out.println(busRoutes.get(i).getRoute());
//			System.out.println(busRoutes.get(i).getSchedule());
//		}
//		for(int i = 0; i < subwayRoutes.size(); i++) {
//			System.out.println(subwayRoutes.get(i).getRoute());
//			System.out.println(subwayRoutes.get(i).getSchedule());
//		}
//
//		for(String email: cardHolders.keySet()) {
//			System.out.println(email);
//			System.out.println(cardHolders.get(email).getCards());
//			System.out.println(cardHolders.get(email).getCards().get(0).getBalance());
//		}
//			HashMap<String, ArrayList<Trip>> trips = loadEvents();
//			System.out.println(trips.get("parlefrancais@gmail.com"));
//			System.out.println(trips.get("Hello@DingDong.com"));
//			System.out.println(trips.get("DannyBadBoi@Yessir.com").get(0).getLocations());
//		
		
	}

	public static HashMap<String, Stop> loadStops() throws FileNotFoundException{
		HashMap<String, Stop> stops = new HashMap<String, Stop>();
		BufferedReader fileStops = new BufferedReader(new FileReader("Resources/BusStops.txt"));
		Scanner scanStops = new Scanner(fileStops);

		while(scanStops.hasNextLine()) {
			String line = scanStops.nextLine();
			String[] data = line.split(",");
			stops.put(data[0], new Stop(data[0], Boolean.parseBoolean(data[1])));
		}

		return stops;
	}

	public static HashMap<String, Station> loadStation() throws FileNotFoundException{
		HashMap<String, Station> stations = new HashMap<String, Station>();
		BufferedReader fileStations = new BufferedReader(new FileReader("Resources/Stations.txt"));
		Scanner scanStations = new Scanner(fileStations);

		while(scanStations.hasNextLine()){
			String line = scanStations.nextLine();
			String[] data = line.split(",");
			stations.put(data[0], new Station(data[0], Boolean.parseBoolean(data[1])));
		}

		return stations;
	}

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

		return busRoutes;
	}

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
		return subwayRoutes;
	}


	public static HashMap<String, CardHolder> loadCardHolders() throws FileNotFoundException{
		HashMap<String, CardHolder> cardHolders = new HashMap<String, CardHolder>();
		HashMap<String, ArrayList<Card>> updatedCards = loadCards();
		HashMap<String, ArrayList<Trip>> trips = loadEvents();
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
		for(String email: trips.keySet()) {
			cardHolders.get(email).loadTrip(trips.get(email));
		}

		return cardHolders;
	}


	public static HashMap<String, ArrayList<Card>> loadCards() throws FileNotFoundException{
		HashMap<String, ArrayList<Card>> cards = new HashMap<String, ArrayList<Card>>();
		BufferedReader fileCards = new BufferedReader(new FileReader("Resources/Cards.txt"));
		Scanner scanCards = new Scanner(fileCards);

		while(scanCards.hasNextLine()) {
			String line = scanCards.nextLine();
			String[] data = line.split(",");
			if(cards.containsKey(data[0])) {
				cards.get(data[0]).add(new Card(Double.parseDouble(data[1]), Integer.parseInt(data[2])));
			}
			else {
				cards.put(data[0], new ArrayList<Card>(Arrays.asList(new Card(Double.parseDouble(data[1]), Integer.parseInt(data[2])))));
			}
		}

		return cards;

	}

	
	public static HashMap<String, ArrayList<Trip>> loadEvents() throws FileNotFoundException{
		HashMap<String, ArrayList<Trip>> trips = new HashMap<String, ArrayList<Trip>>();
		BufferedReader fileEvents = new BufferedReader(new FileReader("Resources/events.txt"));
		Scanner scanEvents = new Scanner(fileEvents);
		HashMap<String, Stop> stops = loadStops();
		HashMap<String, Station> stations = loadStation();
		
		while(scanEvents.hasNextLine()) {
			String line = scanEvents.nextLine();
			ArrayList<String> data = new ArrayList<String>(Arrays.asList(line.split(",")));
			Trip t = new Trip();
			t.addMoneySpentOnTrip(Double.parseDouble(data.get(1)));
			t.addTimeToTrip(Integer.parseInt(data.get(2)));
			for(int i = 3; i < data.size(); i++) {
				if(data.get(i).charAt(0) == '!') {
					t.addLocation(stops.get(data.get(i).substring(1)));
				}
				else {
					t.addLocation(stations.get(data.get(i).substring(1)));
				}
				
			}
			
			if(trips.containsKey(data.get(0))) {
				trips.get(data.get(0)).add(t);
			}
			else {
				trips.put(data.get(0), new ArrayList<Trip>(Arrays.asList(t)));
			}
		}
		
		return trips;
	}

}
