package game;

import game.controller.SupermarketController;
import game.model.Product;
import game.model.ShoppingCart;
import game.model.Supermarket;
import game.view.ConsolePrinter;
import game.view.ConsoleReader;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        ConsolePrinter p = new ConsolePrinter();
        ConsoleReader r = new ConsoleReader();
        Supermarket albertHeijn = new Supermarket();
        SupermarketController market = new SupermarketController(albertHeijn, r, p);

        market.supply(new Product("Soap", 3.00), 100);
        market.supply(new Product("Cereals", 2.50), 50);
        market.supply(new Product("Chinese vegetables", 5.00), 20);
        market.supply(new Product("Yoghourt", 2.00), 0);
        market.supply(new Product("Diapers", 10.00), 1);

        market.shop();
    }
}
