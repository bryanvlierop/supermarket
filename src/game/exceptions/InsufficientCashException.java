package game.exceptions;

public class InsufficientCashException extends Exception {
    public InsufficientCashException() {
        super("Je komt geld tekort!");
    }
}
