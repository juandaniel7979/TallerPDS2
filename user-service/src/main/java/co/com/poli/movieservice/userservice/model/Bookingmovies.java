package co.com.poli.movieservice.userservice.model;

import lombok.Data;

@Data
public class Bookingmovies {
    private Long id;
    private Long movieId;
    private Movie movie;
}
