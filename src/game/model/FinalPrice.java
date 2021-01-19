package game.model;

public class FinalPrice {
    private final double price;
    private final double receivedDiscount;

    public FinalPrice(double price, double receivedDiscount) {
        this.price = price;
        this.receivedDiscount = receivedDiscount;
    }

    public double getPrice() {
        return price;
    }

    public double getReceivedDiscount() {
        return receivedDiscount;
    }
}
