package BookMyShow.models;

import java.util.List;

public class Screen {
    String name;
    String id;
    Theatre theatre;
    List<Seat> seats;

    public Screen(String name, String id, Theatre theatre, List<Seat> seats) {
        this.name = name;
        this.id = id;
        this.theatre = theatre;
        this.seats = seats;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}
