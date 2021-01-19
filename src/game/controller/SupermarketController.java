package game.controller;

import game.exceptions.InsufficientCashException;
import game.model.Product;
import game.model.ShoppingCart;
import game.model.Supermarket;
import game.view.ConsolePrinter;
import game.view.ConsoleReader;

import java.text.NumberFormat;
import java.util.Locale;

public class SupermarketController {
    private final ConsoleReader reader;
    private final ConsolePrinter printer;
    private final NumberFormat numberFormat;

    private final Supermarket market;

    public SupermarketController() {
        numberFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        reader = new ConsoleReader();
        printer = new ConsolePrinter();
        market = new Supermarket();
    }

    public void checkout(ShoppingCart cart) {
        printer.show("Goedemiddag!");

        var price = market.getRegister().determineFinalPrice(cart);
        printer.show("De boodschappen kosten totaal " + numberFormat.format(price.getPrice()));

        var discount = price.getReceivedDiscount();
        if (discount > 0)
            printer.show("U heeft hierover " + numberFormat.format(discount) + " korting ontvangen.");

        double customerCash = askNumberRelatedQuestionToCostumer("Voer het bedrag dat u wilt betalen in");
        double possibleChange;
        try {
            possibleChange = market.getRegister().checkout(price.getPrice(), customerCash);
        } catch (InsufficientCashException e) {
            printer.show(numberFormat.format(customerCash) + " is helaas onvoldoende.");
            printer.show("We zien u graag een andere keer terug bij onze supermarkt!");
            return;
        }

        if (possibleChange > 0)
            printer.show("U krijgt " + numberFormat.format(possibleChange) + " terug.");
        printer.show("Hartelijk bedankt voor uw aankoop!");
    }

    public Product getProductByName(String name) {
        return market.getProductByName(name);
    }

    public void supply(Product p, int amount) {
        market.addStock(p, amount);
    }

    private double askNumberRelatedQuestionToCostumer(String question) {
        var userInput = askQuestionToCostumer(question);
        double result;
        try {
            result =  Double.parseDouble(userInput);
        } catch ( Exception e) {
            return askNumberRelatedQuestionToCostumer(question);
        }
        return result;
    }

    private String askQuestionToCostumer(String question) {
        printer.show(question);
        return reader.askUserInput();
    }
}
