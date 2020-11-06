package transitapp;

import java.lang.Math;
import java.util.ArrayList;

public class Trip {

    private static double MAX_COST;
    private static double MAX_DURATION;
    private double currTripCost;
    private double currTime; // Time units TBD
    private ArrayList<Location> locationsTravelled;

    public Trip() {
        this.currTripCost = 0.0;
        locationsTravelled = new ArrayList<Location>();
    }

    public void addLocation(Location loc) {
        this.locationsTravelled.add(loc);
    }

    public static void main(String[] args) {

    }

    public int stationsTravelled(Location start, Location end, TransitRoutes subRoute) {
        return Math.abs(subRoute.getRoute().indexOf(start) -  subRoute.getRoute().indexOf(end));
    }

    public double getMaxCost() {
        return MAX_COST;
    }
}
