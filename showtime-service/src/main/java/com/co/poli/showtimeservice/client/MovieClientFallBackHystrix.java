package com.co.poli.showtimeservice.client;

import com.co.poli.showtimeservice.helpers.Response;
import com.co.poli.showtimeservice.helpers.ResponseBuild;
import com.co.poli.showtimeservice.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieClientFallBackHystrix implements com.co.poli.showtimeservice.client.MovieClient {

    private final ResponseBuild builder;

    @Override
    public Response findById(Long id) {
        Movie movie = new Movie();
        movie.setTitle("");
        movie.setDirector("");
        movie.setRating(0);
        return  builder.success(movie);
    }

}
