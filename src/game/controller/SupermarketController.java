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

    public void supply(Product p, int amount) {
        market.addStock(p, amount);
    }

    public void shop() {
        var shoppingCart = new ShoppingCart();
        String userInput = "";
        while(!userInput.equalsIgnoreCase("kassa")) {
            printer.show("Waar bent u naar op zoek?");
            userInput = reader.askUserInput();
            Product pr = market.getProductByName(userInput);
            if (pr == null)
                printer.show("Het door u opgegeven product kon niet worden gevonden.");
            else if (!market.hasStock(pr))
                printer.show("Het door u opgegeven product is niet op voorraad.");
            else {
                market.decreaseStock(pr);
                shoppingCart.addProduct(pr);
                printer.show("Het product is toegevoegd aan uw winkelwagen");
            }
        }
        checkout(shoppingCart);
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

    private void checkout(ShoppingCart cart) {
        printer.show("Goedemiddag!");

        printer.show("");
        for (Product p : cart.getShoppingList().keySet()) {
            String multiple = cart.getShoppingList().get(p) > 1 ? cart.getShoppingList().get(p) + "x " : "";
            printer.show("*Scant "  + multiple + p.getName() + "*");
        }
        printer.show("");

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

        for (Product p : cart.getShoppingList().keySet()) {
            market.buyProduct(p, cart.getShoppingList().get(p));
        }
    }

    private String askQuestionToCostumer(String question) {
        printer.show(question);
        return reader.askUserInput();
    }
}
