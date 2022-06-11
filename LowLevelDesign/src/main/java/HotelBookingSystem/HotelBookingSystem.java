package HotelBookingSystem;

import CarRentalService.entity.Location;

import java.util.*;

class Hotel {
    int hotelId;
    String name;
    Location location;
    List<Room> rooms;
    Double rating;
    List<Comment> reviews;
}

class Address {
    String city;
    String zipcode;
    String latitude;
    String longitude;
    String country;
    String state;
}

class Room {
    int roomNo;
    int hotelId;
    int floor;
    RoomType roomType;
    int price;
    RoomStatus roomStatus;
}

enum RoomType {
    DELUXE, PREMIUM;
}

enum RoomStatus {
    AVAILABLE, BOOKED, RESERVED, UNDER_MAINTAINANCE;
}

class Comment {
    int commentId;
    String username;
    String emailId;
    String comment;
}

class User {
    String userId;
    String email;
    Location address;
    String username;
    String password;
}

class RoomBookingDetails {
    Long bookingId;
    User user;
    Hotel hotel;
    Room room;
    Date fromDate;
    Date toDate;
    Payment payment;
    BookingStatus bookingStatus;
}

class Payment {
    int paymentId;
    String paymentType;
    String PaymentStatus;
    Double amount;
}

enum BookingStatus {
    CONFIRMED, EXPIRED, CANCELLED, DECLINED, REFUNDED;
}

class NotificationService {
    String notificationType;
    String notification;
}

public class HotelBookingSystem {
    Map<Location, Hotel> hotelLocationMap;
    List<User> users;

    public void createOrLoginUser(){}
    public List<Hotel> searchHotelByLocation(Location location, Date date){return null;}
    public void placeOrder(User user, RoomBookingDetails roomBookingDetails){}
}