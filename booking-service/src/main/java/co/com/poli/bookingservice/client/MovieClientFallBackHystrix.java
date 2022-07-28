package co.com.poli.bookingservice.client;

import co.com.poli.bookingservice.helpers.Response;
import co.com.poli.bookingservice.helpers.ResponseBuild;
import co.com.poli.bookingservice.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieClientFallBackHystrix implements co.com.poli.bookingservice.client.MovieClient {

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
