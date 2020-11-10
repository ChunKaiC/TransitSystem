package transitapp;

import com.sun.tools.corba.se.idl.InterfaceGen;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.lang.Math;
import java.time.LocalDateTime;

public class CardHolder {

    private String name;
    private String email;
    private ArrayList<Trip> trips = new ArrayList<Trip>();
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Trip currTrip;
    private boolean onRoute;
    private Location tapOnLocation;
    private Location tapOffLocation;
    private static ArrayList<CardHolder> allCardHolders = new ArrayList<CardHolder>();

    public CardHolder(String name, String email) {
        this.name = name;
        this.email = email;
        this.onRoute = false;
        this.cards.add(new Card());
        this.currTrip = null;
        this.tapOnLocation = null;
        this.tapOffLocation = null;
        CardHolder.allCardHolders.add(this);
    }
    
    public ArrayList<Trip> getRecentTrips() {
		if (this.trips.size() <= 2) {
		    return this.trips;
        }
		else {
            ArrayList<Trip> recents = new ArrayList<>();
            recents.add(trips.get(trips.size() - 3));
            recents.add(trips.get(trips.size() - 2));
            recents.add(trips.get(trips.size() - 1));
            return recents;
        }
    }

    public ArrayList<Trip> getTrips() {
        return this.trips;
    }

    public void deleteAllCards () {
        this.cards.clear();
    }
    
    public ArrayList<Card> getCards() {
		return this.cards;
    }
    
    // faisal has written the following code for controller
    public void addCard (Card card) {
    	this.cards.add(card);
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getEmail() {
    	return this.email;
    }
    // axel code bellow

    public static void main(String[] args) {

    }

    public void changeName(String name) {
        this.name = name;
    }

    public double averageMonthlyCost() {
        ArrayList<LocalDate> months = new ArrayList<LocalDate>();
        double cost = 0.0;

        for (Trip trip : this.trips) {
            LocalDate date = LocalDate.of(trip.getStartTime().getYear(), trip.getStartTime().getMonth(), 1);
            if (!months.contains(date)) {
                months.add(date);
            }
            cost += trip.getMoneySpentOnTrip();
        }
        if (months.size() == 0) {
            return 0.0;
        }
        return cost / months.size();
    }
    
    public boolean tapOn(Location location, int card_id, LocalDateTime time) {
        Card current_card = cards.get(card_id); // Must be able to get card from the list based on its id.

        if (!current_card.isActivated()) {
            return false;
        }

        if (this.tapOffLocation != location) {
            // create a new trip if tapping on at a different location then tapped off location
            this.currTrip = new Trip();
        }
        double fare = findFare(location);

        if (tapOnLocation != null) {
            // this means the person never tapped off (previous trip)
            // deduct max cost
            current_card.deductFare(this.currTrip.getMaxCost());
            // so at this point, the person may not have balance to tap on again
            }
        
        if ( this.currTrip.getTimeOnTrip() < this.currTrip.getMAX_RIDE_TIME() && 
        		this.currTrip.getMoneySpentOnTrip() < this.currTrip.getMaxCost()) {
            this.onRoute = true;
            this.tapOnLocation = location;
            this.currTrip.addLocation(location);
        	return true;
        }

        if (current_card.hasBalance()) {
        	
            this.onRoute = true;
            this.tapOnLocation = location;
            this.currTrip.addLocation(location);

            // For bus
            if (location instanceof Stop) {
                current_card.deductFare(fare);
                this.currTrip.setStartTime(time);
            }

            return true;
        }
        return false;
    }

    public void tapOff(Station location, int card_id, LocalDateTime time) {
        // Only for subway stations
        Card current_card = findCard(this.cards, card_id); // Must be able to get card from the list based on its id.
        if (current_card.isActivated())
        {
            this.onRoute = false;
            double fare = findFare(location);
            this.currTrip.addLocation(location);

            double numStations = this.currTrip.stationsTravelled((Station) this.tapOnLocation, location, location.getAllStations());
            double cost = numStations * fare;

            if (tapOnLocation == null) {
                // The cardHolder never tapped on, so we will charge them the max cost
                cost = location.getAllStations().indexOf(location) * fare;
            }
            int travelTime = (int) (Duration.between(time, this.currTrip.getStartTime()).toMinutes());
            if (cost > this.currTrip.getMaxCost() || travelTime > this.currTrip.getMAX_RIDE_TIME())
            // if the trip costs more than the max ($6) or the person has been riding for
            // more than 3 hours (180 minutes)
            {
                cost = this.currTrip.getMaxCost();
            }

            this.currTrip.addTimeToTrip(travelTime);
            current_card.deductFare(cost);
            this.currTrip.addMoneySpentOnTrip(cost);
            this.tapOnLocation = null;
            this.tapOffLocation = location;
            this.currTrip.setStartTime(time);
        }
    }

    /*
    Precondition: the card id is a valid id in the list of card objects
     */
    public Card findCard(ArrayList<Card> cards, int id) {
        Card found = null;
        for (Card card: cards) {
            if (card.getCard_id() == id) {
                found = card;
            }
        }
        return found;
    }


    public double findFare(Location location) {
        double fare = 0;
        if (location instanceof Station) {
            fare = TransitRoutes.getSubwayFare();
        }
        else if (location instanceof Stop) {
            fare = TransitRoutes.getBusFare();
        }
        return fare;
    }
}
