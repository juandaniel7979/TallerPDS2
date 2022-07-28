package com.co.poli.showtimeservice.services;

import com.co.poli.showtimeservice.client.MovieClient;
import com.co.poli.showtimeservice.entity.Showtime;
import com.co.poli.showtimeservice.entity.ShowtimeItems;
import com.co.poli.showtimeservice.model.Movie;
import com.co.poli.showtimeservice.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieClient movieClient;

    @Override
    public void save(Showtime showtime) {
        showtimeRepository.save(showtime);
    }

    @Override
    public void delete(Showtime showtime) {
        showtimeRepository.delete(showtime);
    }

    @Override
    public List<Showtime> findAll() {
        List<Showtime> items =showtimeRepository.findAll();
        items.stream()
                .map(showtime -> {
                    return showtime = findById(showtime.getId());
                })
                .collect(Collectors.toList());
        return showtimeRepository.findAll();
    }

    @Override
    public Showtime findById(Long id) {
        Showtime showtime = showtimeRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        List<ShowtimeItems> itemList = showtime.getItems()
                .stream()
                .map(showtimeItem -> {
                    Movie movie = modelMapper.map(movieClient.findById(showtimeItem.getMovieId()).getData(), Movie.class);
                    showtimeItem.setMovie(movie);
                    return showtimeItem;
                })
                .collect(Collectors.toList());
        return showtimeRepository.findById(id).orElse(null);
    }

    @Override
    public Showtime update(Showtime showtime) {
        Showtime showtime1 = showtimeRepository.findById(showtime.getId()).orElse(null);
        showtime1.setId(showtime.getId());
        showtime1.setDate(showtime.getDate());
        showtime1.setItems(showtime.getItems());
        showtimeRepository.save(showtime1);
        return showtimeRepository.findById(showtime1.getId()).orElse(null);
    }

    @Override
    public Boolean listOfIds(Long movieId) {
        List<Showtime> showtimes = findAll();
        Boolean devolver = showtimes.stream()
                .anyMatch(item -> item.getItems()
                        .stream()
                        .anyMatch(movie -> movie.getMovieId() == movieId)
                );
        return devolver;
    }

}

