package transitapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class StartUp {
	public static void main(String[] args) throws FileNotFoundException {
		load();
	}
	
	public static void load() throws FileNotFoundException{
		//Hash maps and Lists of information needed
		
		HashMap<String, Stop> stops = new HashMap<String, Stop>();
		HashMap<String, Station> stations = new HashMap<String, Station>();
		ArrayList<TransitRoutes> busRoutes = new ArrayList<TransitRoutes>();
		ArrayList<TransitRoutes> subwayRoute = new ArrayList<TransitRoutes>();
		HashMap<String, CardHolder> cardHolders = new HashMap<String, CardHolder>();
		HashMap<String, ArrayList<Card>> cards = new HashMap<String, ArrayList<Card>>();
		//****************************************************************************
		//Files being read in
		
		BufferedReader fileStops = new BufferedReader(new FileReader("Resources/BusStops.txt"));
		BufferedReader fileStations = new BufferedReader(new FileReader("Resources/Stations.txt"));
		BufferedReader fileBusRoutes = new BufferedReader(new FileReader("Resources/BusRoutes.txt"));
		BufferedReader fileStationRoute = new BufferedReader(new FileReader("Resources/StationRoutes.txt"));
		BufferedReader fileCardHolders = new BufferedReader(new FileReader("Resources/CardHolders.txt"));
		BufferedReader fileCards = new BufferedReader(new FileReader("Resources/Cards.txt"));
		//Scanners
		Scanner scanStops = new Scanner(fileStops);
		Scanner scanStations = new Scanner(fileStations);
		Scanner scanBusRoutes = new Scanner(fileBusRoutes);
		Scanner scanStationRoute = new Scanner(fileStationRoute);
		Scanner scanCardHolders = new Scanner(fileCardHolders);
		Scanner scanCards = new Scanner(fileCards);
		
		
		while(scanStops.hasNextLine()) {
			String line = scanStops.nextLine();
			String[] data = line.split(",");
			stops.put(data[0], new Stop(data[0], Boolean.parseBoolean(data[1])));
		}
		
		while(scanStations.hasNextLine()){
			String line = scanStations.nextLine();
			String[] data = line.split(",");
			stations.put(data[0], new Station(data[0], Boolean.parseBoolean(data[1])));
		}
		
		while(scanBusRoutes.hasNextLine()) {
			String line = scanBusRoutes.nextLine();
			ArrayList<String> data = new ArrayList<String>(Arrays.asList(line.split(",")));
			ArrayList<Location> s = new ArrayList<Location>();
			for(int i=1; i < data.size()-1; i++) {
				s.add(stops.get(data.get(i)));
			}
			System.out.println(s);
			busRoutes.add(new TransitRoutes(data.get(0), s, Double.parseDouble(data.get(data.size()-1))));
		}
		
		while(scanStationRoute.hasNextLine()) {
			String line = scanStationRoute.nextLine();
			ArrayList<String> data = new ArrayList<String>(Arrays.asList(line.split(",")));
			ArrayList<Location> s = new ArrayList<Location>();
			for(int i=1; i < data.size()-1; i++) {
				s.add(stations.get(data.get(i)));
			}
			System.out.println(s);
			
		}
	}

	
}
