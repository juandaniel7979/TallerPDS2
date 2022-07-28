package com.co.poli.showtimeservice.repository;

import com.co.poli.showtimeservice.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

//    Showtime findByMovieId (Long movieId);

}
