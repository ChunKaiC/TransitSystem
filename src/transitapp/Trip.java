package transitapp;

import java.lang.Math;
import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDateTime;

public class Trip {

    public static double MAX_COST = 6.0;
    public static int MINUTE_GRACE_PERIOD = 120;
    public static int MAX_RIDE_TIME = 180; // in minutes
    
    private int timeOnTrip; // in minutes
    private double moneySpentOnTrip;
    // ^ if it reaches 2 hours, make a new trip, otherwise free if spent $6 already
    private ArrayList<Location> locationsTravelled;
    private ArrayList<String> taps;
    private ArrayList<LocalDateTime> times;
    private ArrayList<Integer> cardUsed;
    private LocalDateTime startTime;

    public Trip() {
        this.moneySpentOnTrip = 0.0;
        this.timeOnTrip = 0;
        locationsTravelled = new ArrayList<Location>();
    }
    
    public void updateTimeOnTrip() {
    	if (this.times.size() > 1) {
    		this.timeOnTrip = (int) (Duration.between(this.times.get(0), this.times.get(this.times.size())).toMinutes());
    	}
    }
    
    public double getMINUTE_GRACE_PERIOD() {
    	return MINUTE_GRACE_PERIOD;
    }
    
    
    public void addCardUsed(Integer cardID) {
    	this.cardUsed.add(cardID);
    }
    
    public ArrayList<Integer> getCardUSed(){
    	return this.cardUsed;
    }
    
    public void addTimes(LocalDateTime time) {
    	this.times.add(time);
    }
    
    public ArrayList<LocalDateTime> getTimes(){
    	return this.times;
    }
    
    public void addTaps(String tap) {
    	this.taps.add(tap);
    }
    
    public ArrayList<String> getTaps(){
    	return this.taps;
    }

    public void addMoneySpentOnTrip (double amount) {
        this.moneySpentOnTrip += amount;
    }

    public double getMoneySpentOnTrip () {
        return this.moneySpentOnTrip;
    }

    public void addLocation(Location loc) {
        this.locationsTravelled.add(loc);
    }

    public int stationsTravelled(Station start, Station end, ArrayList<Station> stations) {
        return Math.abs(stations.indexOf(start) -  stations.indexOf(end));
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
        return MAX_RIDE_TIME;
    }

    public void addTimeToTrip(int time) {
        this.timeOnTrip += time;
    }

    public int getTimeOnTrip() {
        return this.timeOnTrip;
    }
    
    public ArrayList<Location> getLocations(){
    	return this.locationsTravelled;
    }
    
    @Override 
    public String toString() {
    	String string = this.startTime.getMonth().getValue() + "/" + 
    			this.startTime.getDayOfWeek().getValue() + "/"+ 
    			this.startTime.getYear() + ",";
    	
    	for (int i = 0; i < this.locationsTravelled.size(); i++) {
    		if (i != this.locationsTravelled.size() - 1) {
    			string = string + this.locationsTravelled.get(i).getLocation() + "->";
    		} else {
    			string = string + this.locationsTravelled.get(i).getLocation();
    		}
    	}
    	return string;
    }
    
    public static void main(String[] args) { 
    	Trip t = new Trip();
    	t.addLocation(new Stop("stop1", false));
    	t.addLocation(new Stop("stop2", false));
    	t.addLocation(new Stop("stop3", false));
    	
    	System.out.println(t);
    }
}