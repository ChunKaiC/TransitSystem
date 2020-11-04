package transitapp;

import java.util.ArrayList;

public class CardHolder {

    private String name;
    private String email;
    private ArrayList<Trip> recent_trips;
    private ArrayList<Card> cards;
    private int currTrip;
    private boolean onRoute;

    public CardHolder(String name, String email) {
        this.name = name;
        this.email = email;
        this.onRoute = false;
   
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

    public boolean tapOn(Location location, int card_id) {
        this.onRoute = true;
        Card current_card = cards.get(index); // pass in helper that finds card based on id
        if (current_card.hasBalance()) {
            if (location instanceof Station) {
                current_card.deductFare(BusRoute.stationFare); //
            } else if (location instanceof Stop) {
                current_card.deductFare(BusRoute.busFare); //
            }
            return true;
        }
        return false;
    }

    public void tapOff(Location location, int card_id) {
        this.onRoute = false;

        this.onRoute = true;
        if (location instanceof Station) {
            // calculate number of stations travelled from Trip class
            // and calculate cost
            return null;
        }
    }
}
