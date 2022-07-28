package co.com.poli.movieservice.services;

import co.com.poli.movieservice.entity.Movie;

import java.util.List;

public interface MovieService {
    void save(Movie movie);

    Boolean delete(Movie movie);

    List<Movie> findAll();

    Movie findById(Long id);

    List<Movie> findByRating(Long rating);
}
