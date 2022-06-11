package FlightBookingSystem;

import BookMyShow.models.BookingStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

class Flight {
    int flightNo;
    Airline airline;
    int seatCapacity;
}

class Airline {
    String name;
    int id;
    List<Flight> flights;
}

class Seat {
    int seatNo;
    SeatClass seatClass;
}

enum SeatClass {
    BUSINESS, ECONOMY;
}

class FlightSchedule {
    Flight flight;
    Airport source;
    Airport dest;
    String gate;
    Date startTime;
    Date endTime;
    Double journeyTime;
    FlightStatus flightStatus;
    List<FlightSeat> flightSeat;
}

enum FlightStatus {
    DELAYED, CANCELLED, SCHEDULED, DEPARTED;
}

class FlightSeat extends Seat{
    Double price;
    SeatStatus seatStatus;
}

enum SeatStatus {
    BOOKED, UNBOOKED, LOCKED, RESERVED;
}

class Airport {
    int id;
    String name;
    Location location;
    List<Flight> flightList;
}

class Location {
    int id;
    String latitude;
    String longitude;
    String city;
    String zipcode;
}

class User {
    int id;
    String name;
    String emailId;
    Location location;
}

class BookingDetail {
    String PNR;
    FlightSchedule flightSchedule;
    User user;
    Location start;
    Location end;
    Date startTime;
    Date endTime;
    Payment payment;
    Map<User, FlightSeat> seats;
    BookingStatus bookingStatus;
}

class Payment {
    int id;
    Double amount;
    String type;
    String status;
}

class FlightBookingSystem {
    List<User> users;
    List<Flight> flights;

    public List<Flight> getFlightDetails(Location start, Location end,
                                         Date travelDate) { return null;}
    public void bookingFlight(User user, FlightSchedule flightSchedule){}
    public void confirmBooking(BookingDetail bookingDetail){};
}