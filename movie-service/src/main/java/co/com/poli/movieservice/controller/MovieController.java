package co.com.poli.movieservice.controller;

import co.com.poli.movieservice.entity.Movie;
import co.com.poli.movieservice.helpers.ErrorMessage;
import co.com.poli.movieservice.helpers.Response;
import co.com.poli.movieservice.helpers.ResponseBuild;
import co.com.poli.movieservice.services.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ResponseBuild responseBuild;

    @GetMapping
    public Response findAll() {
        List<Movie> movie = movieService.findAll();
        if (movie.isEmpty()) {
            return responseBuild.failed(movie);
        }
        return responseBuild.success(movie);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return responseBuild.failed(null);
        }
        return responseBuild.success(movie);
    }

    @GetMapping("rating/{rating}")
    public Response findByRating(@PathVariable("rating") Long rating) {
        List<Movie> listMovies = movieService.findByRating(rating);
        if (listMovies == null) {
            return responseBuild.failed(null);
        }
        return responseBuild.success(listMovies);
    }

    @PostMapping
    public Response save(@Valid @RequestBody Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return responseBuild.failed(this.formatMessage((result)));
        }
        movieService.save(movie);
        return responseBuild.success(movie);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return responseBuild.failed("No existe la pelicula");
        }
        if (movieService.delete(movie)) {
            return responseBuild.success("deleted movie");
        } else {
            return responseBuild.failed("Booking or showtime associate");
        }
    }

    public String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}

