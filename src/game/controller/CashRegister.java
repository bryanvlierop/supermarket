package game.controller;

import game.model.Product;
import game.model.ShoppingCart;
import game.view.ConsolePrinter;
import game.view.ConsoleReader;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class CashRegister {

    private final ConsoleReader reader;
    private final ConsolePrinter printer;
    private final NumberFormat numberFormat;

    public CashRegister() {
        numberFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        reader = new ConsoleReader();
        printer = new ConsolePrinter();
    }

    public void checkout(ShoppingCart s) {
        var price = calculatePrice(s.getShoppingList());
        printer.show("Het kost totaal " + formatEuro(price) + ".");

        var discount = calculateDiscount(price);
        if (discount > 0) {
            printer.show("Gelukkig krijg je een korting van " + formatEuro(discount) + "!");
            price -= discount;
            printer.show("Je betaalt nu " + formatEuro(price) + ".");
        }

        var paid = 1000;
        var change = calculateChange(price, paid);
        if (change < 0)
            printer.show("Je komt geld tekort!");
        else if (change > 0)
            printer.show("Je krijgt " + formatEuro(change) + " terug.");

        printer.show("Bedankt voor je aankoop!");
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

    private String formatEuro(double price) {
        return numberFormat.format(price);
    }
}
