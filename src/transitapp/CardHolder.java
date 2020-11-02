package transitapp;

import java.util.ArrayList;

public class CardHolder {

    private String name;
    private String email;
    private ArrayList<T> recent_trips;
    private ArrayList<T> cards;
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

    public void tapOn() {
        this.onRoute = true;

        // TODO
    }

    public void tapOff() {
        this.onRoute = false;

        // TODO
    }
}
