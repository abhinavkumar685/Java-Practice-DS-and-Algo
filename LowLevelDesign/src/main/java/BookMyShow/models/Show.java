package BookMyShow.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Show {
    String id;
    Movie movie;
    Screen screen;
    Date startTime;
    int showDuration;
}
