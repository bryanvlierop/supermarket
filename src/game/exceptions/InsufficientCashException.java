package game.exceptions;

public class InsufficientCashException extends Exception {
    public InsufficientCashException() {
        super("U komt geld tekort.");
    }
}
