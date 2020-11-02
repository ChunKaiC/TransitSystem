package transitapp;

public class Card {

    private double balance;
    private static int CARDS_ISSUED; // total number of cards issued to all users
    private int card_id; // unique identifier

    public Card() {
        this.balance = 0.0;
        this.card_id = 0; // How are we going to generate a unique number?
    }

    public static void main(String[] args) {

    }

    public void addBalance(int amount) {
        this.balance += amount;
    }

    public void deductFare(int amount) {
        this.balance -= amount;
    }
}
