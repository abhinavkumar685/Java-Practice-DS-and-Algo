package BookMyShow.models;

import BookMyShow.exceptions.InvalidStateException;

import java.util.List;

public class Booking {
    String id;
    Show show;
    List<Seat> seatsBooked;
    String user;
    BookingStatus bookingStatus = BookingStatus.CREATED;
    // Transaction transactionID;

    public Booking(String id, Show show, List<Seat> seatsBooked, String user,
                   BookingStatus bookingStatus) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = bookingStatus;
    }

    public void confirmBooking() throws InvalidStateException {
        if(this.bookingStatus != BookingStatus.CREATED) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() throws InvalidStateException {
        if(this.bookingStatus != BookingStatus.CREATED) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }
}
