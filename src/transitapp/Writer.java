package transitapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
//    	StartUp.main();
//        System.out.println(StartUp.subwayRoutes.get(0).getRoute());
//        System.out.println(StartUp.cards.get("parlefrancais@gmail.com"));
//        StartUp.cards.get("parlefrancais@gmail.com").get(0).desactivate();
//        removeCard(StartUp.cards.get("parlefrancais@gmail.com").get(0), StartUp.cardHolders.get("parlefrancais@gmail.com"));
//        writeCard("parlefrancais@gmail.com", "25.0", "1", false, LocalDate.of(2019, 10, 12));
//        StartUp.main();
//        System.out.println(StartUp.cards.get("parlefrancais@gmail.com"));
//        
    }
	
	
    public static void writeCardHolder(CardHolder client) throws IOException {
        File cardHolderFile = new File("Resources/CardHolders.txt");
        FileWriter writeCardHolders = new FileWriter(cardHolderFile, true);
        PrintWriter pw = new PrintWriter(writeCardHolders);
        pw.println(client.getName() + "," + client.getEmail());
        pw.close();
    }

    public static void writeCard(String email, String balance, String id, boolean active, LocalDate date) throws IOException{
        File cardFile = new File("Resources/Cards.txt");
        FileWriter writeCard = new FileWriter(cardFile, true);
        PrintWriter pw = new PrintWriter(writeCard);
        pw.println(email + "," + balance + "," + id + ","+Boolean.toString(active) + ","
        +Integer.toString(date.getYear()) +"-"+ Integer.toString(date.getMonthValue()) 
        + "-" + Integer.toString(date.getDayOfMonth()));
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
        FileWriter writeSettingsFile = new FileWriter(settingsFile);
        PrintWriter pw = new PrintWriter(writeSettingsFile);
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
    
    
    //Removing methods
    public static void removeCardHolder(CardHolder client) throws IOException {
    	StartUp.cardHolders.remove(client.getEmail());
    	FileWriter fw = new FileWriter("Resources/CardHolders.txt", false); 
        PrintWriter pw = new PrintWriter(fw);
        pw.close();
        fw.close();
    	for(String key: StartUp.cardHolders.keySet()) {
    		writeCardHolder(StartUp.cardHolders.get(key));
    	}
    	StartUp.main();
    }
    
    public static void removeCard(Card c, CardHolder client) throws IOException{
    	StartUp.cards.get(client.getEmail()).remove(c);
    	FileWriter fw2 = new FileWriter("Resources/Cards.txt", false); 
        PrintWriter pw2 = new PrintWriter(fw2);
        pw2.close();
        fw2.close();
        for(String key: StartUp.cards.keySet()) {
    		for(Card card: StartUp.cards.get(key)) {
    			writeCard(key,Double.toString(card.getBalance()) , Integer.toString(card.getCard_id()), card.isActivated(), card.getTimeInitialized());
    		}
    	}
        fw2.close();
        pw2.close();
        StartUp.main();
    	
    }
    
    
    public static void removeBusStop(Stop busStop) throws IOException{
    	StartUp.stops.remove(busStop.getLocation());
    	FileWriter fw = new FileWriter("Resources/BusStops.txt", false); 
        PrintWriter pw = new PrintWriter(fw);
        pw.close();
        fw.close();
        for(String key: StartUp.stops.keySet()) {
        	writeBusStop(StartUp.stops.get(key));
        }
        StartUp.main();
    }
    
    public static void removeStation(Station station) throws IOException{
    	StartUp.stations.remove(station.getLocation());
    	FileWriter fw = new FileWriter("Resources/Stations.txt", false); 
        PrintWriter pw = new PrintWriter(fw);
        pw.close();
        fw.close();
        for(String key: StartUp.stations.keySet()) {
        	writeStation(StartUp.stations.get(key));
        }
        StartUp.main();
    }
    
    
    public static void removeBusRoute(TransitRoutes busRoute) throws IOException{
    	
    	StartUp.busRoutes.remove(busRoute);
    	FileWriter fw = new FileWriter("Resources/BusRoutes.txt", false); 
        PrintWriter pw = new PrintWriter(fw);
        pw.close();
        fw.close();
        for(TransitRoutes route: StartUp.busRoutes) {
        	writesBusRoute(route);
        }
        StartUp.main();
    }
    
    public static void removeSubwayRoute(TransitRoutes subwayRoute) throws IOException{
    	StartUp.subwayRoutes.remove(subwayRoute);
    	FileWriter fw = new FileWriter("Resources/StationRoutes.txt", false); 
        PrintWriter pw = new PrintWriter(fw);
        pw.close();
        fw.close();
        for(TransitRoutes route: StartUp.subwayRoutes) {
        	writeSubwayRoute(route);
        }
        StartUp.main();
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