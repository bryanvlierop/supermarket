package game;

import game.controller.CashRegister;
import game.model.Product;
import game.model.ShoppingCart;

public class Application {

    public static void main(String[] args) {
	    var product = new Product("Soap", 3.00);
        var product1 = new Product("Cereals", 2.50);
        var product2 = new Product("Chinese vegetables", 5.00);
        var product3 = new Product("Yoghourt", 2.00);
        var product4 = new Product("Diapers", 10.00);

        var register = new CashRegister();
        var shoppingCart = new ShoppingCart();

        //aan het winkelen
        shoppingCart.addProduct(product);

        register.checkout(shoppingCart);
    }
}
