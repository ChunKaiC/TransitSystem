package transitapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Writer {
    public static void main(String[] args) throws IOException{
//    	HashMap<String, CardHolder> c = StartUp.loadCardHolders();
//        Stop s = new Stop("BRUH PLANET", false);
//        Station station = new Station("YESIRRR", true);
//        Station station2 = new Station("GEUWIB", true);
//        LocalTime t1 = LocalTime.of(10, 15);
//        LocalTime t2 = LocalTime.of(10, 45);
//
//        TransitRoutes route = new TransitRoutes("Route number 100", new ArrayList<Location>(Arrays.asList(station, station2)), new ArrayList<LocalTime>(Arrays.asList(t1, t2)));
//
//		removeCardHolder(c.get("BogMan@CS.com"));
        String email = "IlirDema@IFuckingSuckAtMyJob.shodowRealm";
        
        Trip t = new Trip();
        t.addLocation(new Stop("Shadow Realm", false));
        t.addLocation(new Station("We made it to the shaw realm", false));
        t.addMoneySpentOnTrip(2);
        t.addTimeToTrip(100000);
        writeEvent(email, t);
    }
	
	
    public static void writeCardHolder(CardHolder client) throws IOException {
        File cardHolderFile = new File("Resources/CardHolders.txt");
        FileWriter writeCardHolders = new FileWriter(cardHolderFile, true);
        PrintWriter pw = new PrintWriter(writeCardHolders);
        pw.println(client.getName() + "," + client.getEmail());
        pw.close();
    }

    public static void writeCard(String email, String balance, String id, boolean active) throws IOException{
        File cardFile = new File("Resources/Cards.txt");
        FileWriter writeCard = new FileWriter(cardFile, true);
        PrintWriter pw = new PrintWriter(writeCard);
        pw.println(email + "," + balance + "," + id + ","+Boolean.toString(active));
        pw.close();
    }

    public static void writeBusStop(Stop busStop) throws IOException{
        File busStopFile = new File("Resources/BusStops.txt");
        FileWriter writeBusStop = new FileWriter(busStopFile, true);
        PrintWriter pw = new PrintWriter(writeBusStop);
        pw.println(busStop.getLocation() + "," + busStop.getAtInjuction());
        pw.close();

    }

    public static void writeStation(Station station) throws IOException{
        File busStationsFile = new File("Resources/Stations.txt");
        FileWriter writeStationStop = new FileWriter(busStationsFile, true);
        PrintWriter pw = new PrintWriter(writeStationStop);
        pw.println(station.getLocation() + "," + station.getAtInjuction());
        pw.close();
    }

    public static void writesBusRoute(TransitRoutes route) throws IOException{

        File busRoutesFile = new File("Resources/BusRoutes.txt");
        FileWriter writeBusRoutes = new FileWriter(busRoutesFile, true);
        PrintWriter pw = new PrintWriter(writeBusRoutes);
        ArrayList<Location> routesList = route.getRoute();
        ArrayList<LocalTime> schedule = route.getSchedule();
        String line = route.getName();
        for (int i = 1; i < routesList.size(); i++) {
            line = line + "," + routesList.get(i).getLocation() + ",";
            line = line + Integer.toString(schedule.get(i).getHour())+"," + Integer.toString(schedule.get(i).getMinute());
        }
        pw.println(line);
        pw.close();
    }

    public static void writeSubwayRoute(TransitRoutes route) throws IOException{
        File subwayRoutesFile = new File("Resources/StationRoutes.txt");
        FileWriter writeSubwayRoutes = new FileWriter(subwayRoutesFile, true);
        PrintWriter pw = new PrintWriter(writeSubwayRoutes);
        ArrayList<Location> routesList = route.getRoute();
        ArrayList<LocalTime> schedule = route.getSchedule();
        String line = route.getName();
        for (int i = 0; i < routesList.size(); i++) {
            line = line + "," + routesList.get(i).getLocation() + ",";
            line = line + Integer.toString(schedule.get(i).getHour())+"," + Integer.toString(schedule.get(i).getMinute());
        }
        pw.println(line);
        pw.close();
    }
    
    public static void writeFare(double fare, boolean indication) throws IOException {
    	File settingsFile = new File("Resources/Settings.txt");
        FileWriter writeSettingsFile = new FileWriter(settingsFile, true);
        PrintWriter pw = new PrintWriter(writeSettingsFile);
        pw.flush();
        if(indication == true) {
        	pw.println("BusFare:" + Double.toString(fare));
            pw.println("StationFare:" + Double.toString(TransitRoutes.getSubwayFare()));
        }
        else {
        	pw.println("BusFare:" + Double.toString(TransitRoutes.getBusFare()));
            pw.println("StationFare:" + Double.toString(fare));
        }
        
        pw.println("Minute Grace Period:" + Integer.toString(Trip.MINUTE_GRACE_PERIOD));
        pw.println("Max Cost:" + Double.toString(Trip.MAX_COST));
        pw.close();
    }
    
    
    public static void writeStationFare() {}
    
    //Removing methods
    public static void removeCardHolder(CardHolder client) throws IOException {
    	HashMap<String, CardHolder> holders = StartUp.loadCardHolders();
    	holders.remove(client.getEmail());
    	FileWriter fw = new FileWriter("Resources/CardHolders.txt", false); 
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();;
        pw.close();
        fw.close();
    	for(String key: holders.keySet()) {
    		writeCardHolder(holders.get(key));
    	}
    }
    
    public static void removeCard(Card c, CardHolder client) throws IOException{
    	HashMap<String, ArrayList<Card>> cards = StartUp.loadCards();
    	cards.get(client.getEmail()).remove(c);
    	FileWriter fw2 = new FileWriter("Resources/Cards.txt", false); 
        PrintWriter pw2 = new PrintWriter(fw2, false);
        pw2.flush();
        pw2.close();
        fw2.close();
        for(String key: cards.keySet()) {
    		for(Card card: cards.get(key)) {
    			writeCard(key,Double.toString(card.getBalance()) , Integer.toString(card.getCard_id()), card.isActivated());
    		}
    	}
    	
    }
    
    
    public static void removeBusStop(Stop busStop) throws IOException{
    	HashMap<String, Stop> stops = StartUp.loadStops();
    	stops.remove(busStop.getLocation());
    	FileWriter fw = new FileWriter("Resources/BusStops.txt", false); 
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();;
        pw.close();
        fw.close();
        for(String key: stops.keySet()) {
        	writeBusStop(stops.get(key));
        }
    }
    
    public static void removeStation(Station station) throws IOException{
    	HashMap<String, Station> stations = StartUp.loadStation();
    	stations.remove(station.getLocation());
    	FileWriter fw = new FileWriter("Resources/Stations.txt", false); 
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();;
        pw.close();
        fw.close();
        for(String key: stations.keySet()) {
        	writeStation(stations.get(key));
        }
    }
    
    
    public static void removeBusRoute(TransitRoutes busRoute) throws IOException{
    	ArrayList<TransitRoutes> lst = StartUp.loadBusRoutes();
    	lst.remove(busRoute);
    	FileWriter fw = new FileWriter("Resources/BusRoutes.txt", false); 
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();;
        pw.close();
        fw.close();
        for(TransitRoutes route: lst) {
        	writesBusRoute(route);
        }
    }
    
    public static void removeSubwayRoute(TransitRoutes subwayRoute) throws IOException{
    	ArrayList<TransitRoutes> lst = StartUp.loadSubwayRoute();
    	lst.remove(subwayRoute);
    	FileWriter fw = new FileWriter("Resources/StationRoutes.txt", false); 
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();;
        pw.close();
        fw.close();
        for(TransitRoutes route: lst) {
        	writeSubwayRoute(route);
        }
    }
    
    public static void writeEvent(String tap, String location, int card_id, LocalDateTime time, String email) throws IOException{
    	File eventFile = new File("Resources/events.txt");
        FileWriter writeEvent = new FileWriter(eventFile, true);
        PrintWriter pw = new PrintWriter(writeEvent);
        String line = tap + "," + location + "," + "," + card_id + "," + time.getYear() + "," + time.getMonthValue() + "," + 
        		time.getDayOfMonth() + "," + time.getHour() + "," + time.getHour() + "," + email;
        		
        pw.println(line);
        pw.close();
    	
    }
    
    
}