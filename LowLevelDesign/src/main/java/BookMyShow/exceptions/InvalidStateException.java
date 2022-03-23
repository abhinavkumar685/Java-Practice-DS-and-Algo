package BookMyShow.exceptions;

public class InvalidStateException extends Exception {
    public InvalidStateException() {
        super("Invalid Booking State");
    }
}
