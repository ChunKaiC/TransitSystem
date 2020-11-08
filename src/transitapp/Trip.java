package transitapp;

import java.lang.Math;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Trip {

    private static double MAX_COST;
    private static double MAX_DURATION;
    private double currTripCost;
    private int timeOnTrip; // in minutes
    private ArrayList<Location> locationsTravelled;
    private LocalDateTime startTime;
    private int MAX_RIDE_TIME = 180; // in minutes

    public Trip() {
        this.timeOnTrip = 0;
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

    public LocalDateTime getStartTime () {
        return this.startTime;
    }

    public void setStartTime (LocalDateTime time) {
        this.startTime = time;
    }

    public int getMAX_RIDE_TIME() {
        return this.MAX_RIDE_TIME;
    }

    public void addTimeToTrip(int time) {
        this.timeOnTrip += time;
    }

    public int getTimeOnTrip() {
        return this.timeOnTrip;
    }
}
