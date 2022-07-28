package co.com.poli.movieservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Booking {
    private Long id;
    private Long userId;
    private Long showtimeId;
    private User user;
    private Showtime showtime;
    private List<Bookingmovies> items;
}
