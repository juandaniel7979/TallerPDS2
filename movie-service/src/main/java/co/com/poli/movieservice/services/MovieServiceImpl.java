package co.com.poli.movieservice.services;

import co.com.poli.movieservice.client.BookingClient;
import co.com.poli.movieservice.client.ShowtimeClient;
import co.com.poli.movieservice.entity.Movie;
import co.com.poli.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final BookingClient bookingClient;
    private final ShowtimeClient showtimeClient;

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Boolean delete(Movie movie) {
        ModelMapper modelMapper = new ModelMapper();

        String bookingFlag = modelMapper.map(bookingClient.findAllIds(movie.getId()).getData().toString(), String.class);
        String showtimeFlag = modelMapper.map(showtimeClient.findAllIds(movie.getId()).getData().toString(), String.class);

        if (!bookingFlag.equalsIgnoreCase("true") && !showtimeFlag.equalsIgnoreCase("true")) {
            movieRepository.delete(movie);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public List<Movie> findByRating(Long rating) {
        List<Movie> movies = movieRepository.findAll()
                .stream()
                .filter(item -> item.getRating() == rating)
                .collect(Collectors.toList());
        return movies;
    }
}

