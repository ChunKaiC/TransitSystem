package transitapp;

public class Card {

    private double balance;
    private static int CARDS_ISSUED = 0; // total number of cards issued to all users
    private int card_id; // unique identifier

    public Card() {
        this.balance = 19.0;
        CARDS_ISSUED += 1;
        this.card_id = CARDS_ISSUED;
    }

    public static void main(String[] args) {

    }

    public void addBalance(int amount) {
        this.balance += amount;
    }

    public void deductFare(int amount) {
        this.balance -= amount;
    }

    public boolean hasBalance() {return this.balance >= 0; }
}
