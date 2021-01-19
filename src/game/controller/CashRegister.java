package game.controller;

import game.model.Product;
import game.model.ShoppingCart;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class CashRegister {

    private final NumberFormat numberFormat;

    public CashRegister() {
        numberFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
    }

    public void checkout(ShoppingCart s) {
        var price = calculatePrice(s.getShoppingList());
        System.out.println("Het kost totaal " + formatEuro(price) + ".");

        var discount = calculateDiscount(price);
        if (discount > 0) {
            System.out.println("Gelukkig krijg je een korting van " + formatEuro(discount) + "!");
            price -= discount;
            System.out.println("Je betaalt nu " + formatEuro(price) + ".");
        }

        var paid = 1000;
        var change = calculateChange(price, paid);
        if (change < 0) {
            System.out.println("Je komt geld tekort!");
        }
        else if (change > 0) {
            System.out.println("Je krijgt " + formatEuro(change) + " terug.");
        }

        System.out.println("Bedankt voor je aankoop!");
    }

    private double calculatePrice(Map<Product, Integer> shoppingList) {
        double price = 0.00;
        for (Product p : shoppingList.keySet()) {
            price += p.getPrice() * shoppingList.get(p);
        }
        return price;
    }

    private double calculateDiscount(double price) {
        return Math.floor(price / 10) * 0.50;
    }

    private double calculateChange(double price, double paid) {
        return paid - price;
    }

    private String formatEuro(double price) {
        return numberFormat.format(price);
    }
}
