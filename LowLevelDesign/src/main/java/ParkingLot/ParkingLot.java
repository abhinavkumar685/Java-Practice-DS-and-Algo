package ParkingLot;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    List<ParkingFloor> parkingFloors;
    List<EntranceGate> entranceGateList;
    List<ExitGate> exitGateList;
    String parkingLotName;
    String Address;

    public boolean isParkingSpaceAvailableForVehicle(Vehicle vehicle){return true;}
    public void updateParkingAttendant(ParkingAttendant parkingAttendant,
                                       int gateId){}
}

class ParkingFloor {
    String id;
    boolean isFull;
    List<ParkingSpace> parkingSpaces;
    ParkingDisplayBoard parkingDisplayBoard;
}

class Gate {
    String gateId;
    ParkingAttendant parkingAttendant;
}

class EntranceGate extends Gate {
    ParkingTicket getParkingTicket(Vehicle vehicle){return null;}
}

class ExitGate extends Gate {
    ParkingTicket payParkingTicket(ParkingTicket parkingTicket){return null;}
}

class Address {
    String country;
    String zipcode;
    String state;
    String city;
}

class ParkingSpace {
    int spaceId;
    boolean isFree;
    double costPerHour;
    Vehicle vehicle;
    ParkingSpaceType parkingSpaceType;
}

class ParkingDisplayBoard {
    Map<ParkingSpaceType, Integer> freeSpaceMap;
    public void updateFreeSpotsAvailable(ParkingSpaceType parkingSpaceType,
                                         int spaces){}
}

class Account {
    int id;
    String name;
    String username;
    String password;
    Address address;
}

class Admin extends Account {
    public void addParkingFloor(ParkingLot parkingLot, ParkingFloor parkingFloor){}
    public void addParkingSpace(ParkingFloor parkingFloor, ParkingSpace parkingSpace){}
    public void addParkingDisplayBoard(ParkingFloor parkingFloor,
                                       ParkingDisplayBoard parkingDisplayBoard){}
}

class ParkingAttendant extends Account {
    PaymentService paymentService;

    public void processVehicleEntry(Vehicle vehicle){}
    public void processPayment(ParkingTicket parkingTicket,
                               VehicleType vehicleType){}
}

class Vehicle {
    // Can be changed into VehicleItem just like library book management system
    int vehicleId;
    String licenseNumber;
    VehicleType vehicleType;
    ParkingTicket parkingTicket;
    PaymentInfo paymentInfo;
}

class ParkingTicket {
    long ticketId;
    int levelId;
    int spaceId;
    Date vehicleEntryTime;
    Date vehicleExitTime;
    ParkingSpaceType parkingSpaceType;
    double totalCost;
    ParkingTicketStatus parkingTicketStatus;

    public void updateTotalCost(){}
    public void updateVehicleExitTime(Date vehicleExitTime) {}
}

enum PaymentType {
    CASH, UPI, CREDIT, DEBIT;
}

enum ParkingSpaceType {
    BIKE, CAR, TRUCK;
}

enum VehicleType {
    BIKE, CAR, TRUCK;
}

enum ParkingTicketStatus {
    PAID, ACTIVE, EXPIRED;
}

enum PaymentStatus {
    PAID, PENDING, CANCELLED, DECLINED, REFUNDED;
}

class PaymentService {
    public PaymentInfo makePayment(ParkingTicket parkingTicket,
                                   PaymentType paymentType){return null;}
}

class PaymentInfo {
    double amount;
    Date paymentDate;
    int transactionId;
    ParkingTicket parkingTicket;
    PaymentStatus paymentStatus;
}