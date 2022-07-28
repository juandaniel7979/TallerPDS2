package co.com.poli.bookingservice.model;

import lombok.Data;

@Data
public class ShowtimeItems {

    private Long id;
    private Long movieId;
    private co.com.poli.bookingservice.model.Movie movie;

}