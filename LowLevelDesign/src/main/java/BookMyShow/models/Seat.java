package BookMyShow.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Seat {
    String id;
    int rowNum;
    int seatNum;
}
