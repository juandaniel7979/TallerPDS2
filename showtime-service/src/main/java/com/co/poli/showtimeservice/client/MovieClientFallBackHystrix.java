package co.com.poli.showtimeservice.client;

import co.com.poli.showtimeservice.helpers.Response;
import co.com.poli.showtimeservice.helpers.ResponseBuilder;
import co.com.poli.showtimeservice.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieClientFallBackHystrix implements co.com.poli.showtimeservice.client.MovieClient {

    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        Movie movie = new Movie();
        movie.setTitle("");
        movie.setDirector("");
        movie.setRating(0);
        return  builder.success(movie);
    }

}
