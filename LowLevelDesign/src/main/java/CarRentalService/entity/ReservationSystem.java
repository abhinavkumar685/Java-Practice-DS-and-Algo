package CarRentalService.entity;

import java.util.List;
import java.util.Map;

public class ReservationSystem {
    List<Car> carList;
    List<User> userList;
    List<Location> locationList;
    Map<Car, Location> carLocationMap;

    public void getCarByLocation(){}

    public void getCarByType(){}

    public void bookCar(Reserve reserve){}

    public void confirmBooking(){}
}
