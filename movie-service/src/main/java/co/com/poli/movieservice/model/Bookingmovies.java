package co.com.poli.movieservice.model;

import lombok.Data;

@Data
public class Bookingmovies {
    private Long id;
    private Long movieId;
    private Movie movie;
}
