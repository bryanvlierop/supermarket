package game.model;


import game.exceptions.InsufficientCashException;

import java.util.Map;

public class CashRegister {
    public FinalPrice determineFinalPrice(ShoppingCart s) {
        var price = calculatePrice(s.getShoppingList());

        var discount = calculateDiscount(price);
        if (discount > 0)
            price -= discount;

        return new FinalPrice(price, discount);
    }

    public double checkout(double productCosts, double customerCash) throws InsufficientCashException {
        var change = calculateChange(productCosts, customerCash);
        if (change < 0)
            throw new InsufficientCashException();
        return change;
    }

    private double calculatePrice(Map<Product, Integer> shoppingList) {
        double price = 0.00;
        for (Product p : shoppingList.keySet())
            price += p.getPrice() * shoppingList.get(p);
        return price;
    }

    private double calculateDiscount(double price) {
        return Math.floor(price / 10) * 0.50;
    }

    private double calculateChange(double price, double paid) {
        return paid - price;
    }

}
