package co.com.poli.movieservice.client;

import co.com.poli.movieservice.helpers.Response;
import co.com.poli.movieservice.helpers.ResponseBuild;
import co.com.poli.movieservice.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingClientFallBackHystrix implements BookingClient {

    private final ResponseBuild builder;

    @Override
    public Response findAllIds(Long id) {
        Booking booking = new Booking();
        booking.setUserId(0L);
        booking.setUser(null);
        booking.setShowtimeId(0L);
        booking.setShowtime(null);
        booking.setItems(null);
        return builder.success(booking);
    }

}