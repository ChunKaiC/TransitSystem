package transitapp;


import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
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

    /**
     * The constructor for CardHolder
     * @param name: This CardHolder's bane
     * @param email: This CardHolder's email address
     */
    public CardHolder(String name, String email) {
        this.name = name;
        this.email = email;
        this.onRoute = false;
        this.cards.add(new Card());
        this.currTrip = null;
        this.tapOnLocation = null;
        this.tapOffLocation = null;
    }

    /**
     * Add $10 to one of this CardHolder's cards
     * Precondition: card_id is valid id within the cards
     * @param card_id: The card's id (int)
     */
    public void add10toCard(int card_id) {
        Card card = findCard(this.cards, card_id);
        card.addBalance(10);
        AdminUser.addRevenue(10);
    }

    /**
     * Add $20 to one of this CardHolder's cards
     * Precondition: card_id is valid id within the cards
     * @param card_id: The card's id (int)
     */
    public void add20toCard(int card_id) {
        Card card = findCard(this.cards, card_id);
        card.addBalance(20);
        AdminUser.addRevenue(50);
    }

    /**
     * Add $50 to one of this CardHolder's cards
     * Precondition: card_id is valid id within the cards
     * @param card_id: The card's id (int)
     */
    public void add50toCard(int card_id) {
        Card card = findCard(this.cards, card_id);
        card.addBalance(50);
        AdminUser.addRevenue(50);
    }

    /**
     * A getter for the CardHolder's recent trips
     * @return the 3 most recents trips that this CardHolder
     * has taken.
     */
    public ArrayList<Trip> getRecentTrips() {
		if (this.trips.size() <= 2) {
		    return this.trips;
        }
		else {
            ArrayList<Trip> recents = new ArrayList<Trip>();
            recents.add(trips.get(trips.size() - 3));
            recents.add(trips.get(trips.size() - 2));
            recents.add(trips.get(trips.size() - 1));
            return recents;
        }
    }

    /**
     * A getter for all of the CardHolder's trips
     * @return an ArrayList of trips
     */
    public ArrayList<Trip> getTrips() {
        return this.trips;
    }

    /**
     * Load the existing trips to the CardHolder's trips
     * @param ArrayList of trips
     */
    public void loadTrip(ArrayList<Trip> trips) {
    	this.trips = trips;
    }

    /**
     * Delete all the cards this CardHolder has
     */
    public void deleteAllCards () {
        this.cards.clear();
    }

    /**
     * A getter for the CardHolder's cards
     * @return Arraylist of cards
     */
    public ArrayList<Card> getCards() {
		return this.cards;
    }

    /**
     * Add a card to the Cardholder's existing cards
     * @param An exisitng card object
     */
    public void addCard (Card card) {
    	this.cards.add(card);
    }

    /**
     * A getter for the CardHolder's name
     * @return the CardHolder's name
     */
    public String getName() {
    	return this.name;
    }

    /**
     * A getter for the CardHolder's email address
     * @return the CardHolder's email address
     */
    public String getEmail() {
    	return this.email;
    }

    /**
     * A setter method to change the CardHolder's name
     * @param the new name
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * A method used to access the CardHolder's average monthly cost.
     * Divides the total cost by the number of months with at least 1 trip.
     * @return a double of the CardHolder's average monthly cost.
     */
    public double averageMonthlyCost() {
        ArrayList<LocalDate> months = new ArrayList<LocalDate>();
        double cost = 0.0;
        
        // System.out.println(this.trips);

        for (Trip trip : this.trips) {
            LocalDate date = LocalDate.of(trip.getTimes().get(0).getYear(), trip.getTimes().get(0).getMonth(), 1);
            if (!months.contains(date)) {
                months.add(date);
            }
            cost += trip.getMoneySpentOnTrip();
        }
        // System.out.println("Cost: " + cost + ", num months: " + months.size());
        
        if (months.size() == 0) {
            return 0.0;
        }
        return cost / months.size();
    }

    /**
     * The Method CardHolders use to tap on a bus/subway route.
     * Precondition: the card id is a valid card id within the CardHolder's cards
     * @param location: The location where the CardHolder taps on to enter a bus or subway
     * @param card_id: the id of the card used to tap on
     * @param time: the time at which they tap on including the year, month, day, hour and minute
     * @return whether the tapOn was successful: Must have enough money and have the card activated
     * @throws IOException 
     */
    public boolean tapOn(Location location, int card_id, LocalDateTime time,  boolean load) throws IOException {
        Card current_card = cards.get(card_id); // Must be able to get card from the list based on its id.

        if (!current_card.isActivated()) {
            return false;
        }

        // create a new trip if tapping on at a different location then tapped off location or passed grace period or new trip
        if (this.tapOffLocation != location || this.currTrip.getTimeOnTrip() > this.currTrip.getMINUTE_GRACE_PERIOD()) {
            this.currTrip = new Trip();
            this.trips.add(this.currTrip);
        }

        if (tapOnLocation != null && !(this.tapOnLocation instanceof Stop)) {
            // this means the person never tapped off (previous trip)
            // deduct max cost
        	if (!load) {
        		current_card.deductFare(this.currTrip.getMaxCost());
        	}
            // so at this point, the person may not have balance to tap on again
        }     

        if (current_card.hasBalance()) {
        	
        	this.tapOnLocation = location;
            this.currTrip.addLocation(location);
            this.currTrip.addTaps("tapOn");
            this.currTrip.addCardUsed(card_id);
            this.currTrip.addTimes(time);
            
            this.currTrip.updateTimeOnTrip();

            // For bus only
            if (location instanceof Stop) {
            	
            	if (this.currTrip.getMoneySpentOnTrip() == this.currTrip.getMaxCost()) {
            		Writer.writeEvent("tapOn", "?" + location.getLocation(), time, this.email);
            		return true;
                    	
            	} else if (this.currTrip.getMoneySpentOnTrip() < this.currTrip.getMaxCost()) {
            		if (this.currTrip.getMoneySpentOnTrip() - findFare(location) <= this.currTrip.getMaxCost()) {
            			
            			if (!load) {
            				current_card.deductFare(findFare(location));
            			}
                        this.currTrip.addMoneySpentOnTrip(findFare(location));
                        Writer.writeEvent("tapOn", "?" + location.getLocation(), time, this.email);
                        return true;
            		} else {
            			if (!load) {
            				current_card.deductFare(this.currTrip.getMaxCost() - this.currTrip.getMoneySpentOnTrip());
            			}
                        this.currTrip.addMoneySpentOnTrip(this.currTrip.getMaxCost() - this.currTrip.getMoneySpentOnTrip());
                        Writer.writeEvent("tapOn", "?" + location.getLocation(), time, this.email);
                        return true;
            		}
            	}
            }
            Writer.writeEvent("tapOn", "!" + location.getLocation(), time, this.email);
            return true;
        }
        return false;
    }

    /**
     * The method for Cardholders used to tap Off SUBWAYS (busses don't tap off)
     * Precondition: the card id is a valid card id within the CardHolder's cards
     * @param location: where the tap off occurs
     * @param card_id: the id of the card used to tap off (must be same as tap on card!)
     * @param time: the time at which they tap on including the year, month, day, hour and minute
     * @throws IOException 
     */
    public void tapOff(Station location, int card_id, LocalDateTime time, boolean load) throws IOException {
        // Only for subway stations
        Card current_card = findCard(this.cards, card_id); // Must be able to get card from the list based on its id.
        
        if (current_card.isActivated()){
            double fare = findFare(location);
            this.currTrip.addLocation(location);
            this.currTrip.addTaps("tapOff");
            this.currTrip.addTimes(time);

            double numStations = this.currTrip.stationsTravelled((Station) this.tapOnLocation, location, location.getAllStations());
            double cost = numStations * fare;

            // The cardHolder never tapped on, or used the wrong card, so we will charge them the max cost
            if (tapOnLocation == null || this.currTrip.getCardUsed().size() == 0 || 
            		this.currTrip.getCardUsed().get(this.currTrip.getCardUsed().size() - 1) != card_id) {
            	          
                cost = location.getAllStations().indexOf(location) * fare;
            }
            
            //int size = this.currTrip.getTimes().size();
            
            //int travelTime = (int) (Duration.between(time, this.currTrip.getTimes().get(size - 1)).toMinutes());
            
            if (cost  + this.currTrip.getMoneySpentOnTrip() > this.currTrip.getMaxCost())
            // if the trip costs more than the max ($6) or the person has been riding for
            // more than 3 hours (180 minutes)
            {
                cost = this.currTrip.getMaxCost() - this.currTrip.getMoneySpentOnTrip();
            } 
            
            if (!load) {
            	current_card.deductFare(cost);
            }
            Writer.writeEvent("tapOff", "!" + location.getLocation(), time, this.email);
            this.currTrip.updateTimeOnTrip();
            this.currTrip.addMoneySpentOnTrip(cost);
            this.tapOnLocation = null;
            this.tapOffLocation = location;
        }
    }

    /**
     * Find the Card object based on its id
     * Precondition: the card id is a valid id in the list of card objects
     * @param cards: An ArrayList of all cards owned by the CardHolder
     * @param id: The card's id
     * @return the card Object
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

    /**
     * Find the fare of this location (either bus or subway)
     * @param location
     * @return a double representing the transit fare
     */
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

    /**
     * A setter to change the CardHolder's name
     * @param the new name
     */
	public void setName(String text) {
		this.name = text;
	}
	
	public static void main(String[] args) { 
    	System.out.println((int) (Duration.between(LocalDateTime.of(1, 1, 1, 1, 1), LocalDateTime.of(1, 1, 1, 1, 30)).toMinutes()));
    }
}