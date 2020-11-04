package transitapp;

import java.lang.Math;

public class Trip {

    private static double MAX_COST;
    private static double MAX_DURATION;
    private double currTripCost;
    private double currTime; // Time units TBD

    public Trip() {
        this.currTripCost = 0.0;
    }

    public static void main(String[] args) {

    }

    public int stationsTravelled(Location start, Location end) {
        return Math.abs(Location.getAllLocations().indexOf(start) -  Location.getAllLocations().indexOf(end));
    }
}
