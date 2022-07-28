package co.com.poli.showtimeservice.services;

import co.com.poli.showtimeservice.entity.Showtime;

import java.util.List;

public interface ShowtimeService {
    void save(Showtime showtime);

    void delete(Showtime showtime);

    List<Showtime> findAll();

    Showtime findById(Long id);

    Showtime update(Showtime showtime);

    Boolean listOfIds(Long movieId);



}
