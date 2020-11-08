package transitapp;

public class Card {

    private double balance = 19.0;
    private static int CARDS_ISSUED = 0; // total number of cards issued to all users
    private int card_id; // unique identifier

    public Card() {
        CARDS_ISSUED += 1;
        this.card_id = CARDS_ISSUED;
        AdminUser.addRevenue(this.balance);
        
    }
    
    public Card(double balance, int card_id) {
    	CARDS_ISSUED += 1;
    	this.card_id = card_id;
    	this.balance = balance;
    	AdminUser.addRevenue(this.balance);
    }
    public static void main(String[] args) {

    }

    public String toString() {
        return "Card id: " + (Integer)card_id;
    }

    public void addBalance(double amount) {
        this.balance += amount;
        AdminUser.addRevenue(amount);
    }

    public void deductFare(double amount) {
        this.balance -= amount;
    }

    public boolean hasBalance() {
        return this.balance >= 0; }

    public int getCard_id () {
        return card_id;
    }

    public int getCardsIssued() {
        return CARDS_ISSUED;
    }

    public double getBalance() {
        return this.balance;
    }
}
