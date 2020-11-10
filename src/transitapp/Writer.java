package transitapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Writer {
    public static void main(String[] args) throws IOException{
        CardHolder person = new CardHolder("Nicholas Segovia", "STUG4444@hotmail.com");
        Stop s = new Stop("BRUH PLANET", false);
        Station station = new Station("YESIRRR", true);
        Station station2 = new Station("GEUWIB", true);
        LocalTime t1 = LocalTime.of(10, 15);
        LocalTime t2 = LocalTime.of(10, 45);

        TransitRoutes route = new TransitRoutes("Route number 100", new ArrayList<Location>(Arrays.asList(station, station2)), new ArrayList<LocalTime>(Arrays.asList(t1, t2)));

//		writeCardHolder(person);
//		writeCard("STUG4444@hotmail.com", "19", "8");
//		writeBusStop(s);
//		writeStation(station);
//		writeStation(station2);
        writeSubwayRoute(route);


    }
    public static void writeCardHolder(CardHolder client) throws IOException {
        File cardHolderFile = new File("Resources/CardHolders.txt");
        FileWriter writeCardHolders = new FileWriter(cardHolderFile, true);
        PrintWriter pw = new PrintWriter(writeCardHolders);
        pw.println(client.getName() + "," + client.getEmail());
        pw.close();
    }

    public static void writeCard(String email, String balance, String id) throws IOException{
        File cardFile = new File("Resources/Cards.txt");
        FileWriter writeCard = new FileWriter(cardFile, true);
        PrintWriter pw = new PrintWriter(writeCard);
        pw.println(email + "," + balance + "," + id);
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
}