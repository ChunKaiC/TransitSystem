package transitapp;

import java.util.ArrayList;

public class CardHolder {

    private String name;
    private String email;
    private ArrayList<Trip> trips = new ArrayList<Trip>();
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Trip currTrip;
    private boolean onRoute;
    private Location tapOnLocation;

    public CardHolder(String name, String email) {
        this.name = name;
        this.email = email;
        this.onRoute = false;
        this.cards.add(new Card());
        this.currTrip = null;
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

    public boolean tapOn(Location location, int card_id, TransitRoutes route) {
        Card current_card = cards.get(card_id); // Must be able to get card from the list based on its id.
        this.currTrip = new Trip();

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
            }

            return true;
        }
        return false;
    }

    public void tapOff(Station location, int card_id, TransitRoutes route) {
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

            if (cost > this.currTrip.getMaxCost())
            {
                cost = this.currTrip.getMaxCost();
            }
            current_card.deductFare(cost);
        this.tapOnLocation = null;
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
