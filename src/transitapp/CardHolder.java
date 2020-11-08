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
        this.onRoute = true;
        this.tapOnLocation = location;
        this.currTrip = new Trip();
        this.currTrip.addLocation(location);
        Card current_card = cards.get(card_id); // Must be able to get card from the list based on its id.

        if (current_card.hasBalance()) {
            // For bus
            if (location instanceof Stop) {
                current_card.deductFare(route.getFare());
            }

            // For subway
            else if (location instanceof Station) {
                this.tapOnLocation = location;
                current_card.deductFare(route.getFare());
            }
            return true;
        }
        return false;
    }

    public void tapOff(Station location, int card_id, TransitRoutes route) {
        this.onRoute = false;
        this.currTrip.addLocation(location);
        Card current_card = findCard(this.cards, card_id); // Must be able to get card from the list based on its id.
            double amount = this.currTrip.stationsTravelled(this.tapOnLocation, location, route);
            double cost = amount * route.getFare();
            if (cost > this.currTrip.getMaxCost()) {
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
