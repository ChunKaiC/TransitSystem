package transitapp;

import java.time.Duration;
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

    public CardHolder(String name, String email) {
        this.name = name;
        this.email = email;
        this.onRoute = false;
        this.cards.add(new Card());
        this.currTrip = null;
        this.tapOnLocation = null;
        this.tapOffLocation = null;
    }
    
    public ArrayList<Trip> getRecentTrips() {
		return this.trips;
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

    public double averageCost() {
        // TODO
        return 0;
    }

    public boolean tapOn(Location location, int card_id, TransitRoutes route, LocalDateTime time) {
        Card current_card = cards.get(card_id); // Must be able to get card from the list based on its id.

        if (this.tapOffLocation != location) {
            // create a new trip if tapping on at a different location then tapped off location
            this.currTrip = new Trip();
        }

        if (tapOnLocation != null) {
            // this means the person never tapped off (previous trip)
            // deduct max cost
            current_card.deductFare(this.currTrip.getMaxCost());
            // so at this point, the person may not have balance to tap on again
        }

        if (current_card.hasBalance()) {
            this.onRoute = true;
            this.tapOnLocation = location;
            this.currTrip.addLocation(location);

            // For bus
            if (location instanceof Stop) {
                current_card.deductFare(route.getFare());
                this.currTrip.setStartTime(time);
            }

            return true;
        }
        return false;
    }

    public void tapOff(Station location, int card_id, TransitRoutes route, LocalDateTime time) {
        // Only for subway stations
        this.onRoute = false;
        this.currTrip.addLocation(location);
        Card current_card = findCard(this.cards, card_id); // Must be able to get card from the list based on its id.
        double numStations = this.currTrip.stationsTravelled(this.tapOnLocation, location, route);
        double cost = numStations * route.getFare();

        if (tapOnLocation == null) {
            // The cardholder never tapped on, so we will charge them the max cost
            cost = route.getRoute().indexOf(location) * route.getFare();
        }
        int travelTime = (int)(Duration.between(time, this.currTrip.getStartTime()).toMinutes());
        if (cost > this.currTrip.getMaxCost() || travelTime > this.currTrip.getMAX_RIDE_TIME())
        // if the trip costs more than the max ($6) or the person has been riding for
        // more than 3 hours (180 minutes)
        {
            cost = this.currTrip.getMaxCost();
        }
        this.currTrip.addTimeToTrip(travelTime);
        current_card.deductFare(cost);
        this.tapOnLocation = null;
        this.tapOffLocation = location;
        this.currTrip.setStartTime(time);
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
}
