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
    public static void main(String[] args) {

    }

    public void addBalance(int amount) {
        this.balance += amount;
        AdminUser.addRevenue(amount);
    }

    public void deductFare(int amount) {
        this.balance -= amount;
    }

    public boolean hasBalance() {
        return this.balance >= 0; }
}
