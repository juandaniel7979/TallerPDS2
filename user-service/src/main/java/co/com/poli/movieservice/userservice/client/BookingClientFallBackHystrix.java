package co.com.poli.movieservice.userservice.client;

import co.com.poli.movieservice.userservice.helpers.Response;
import co.com.poli.movieservice.userservice.helpers.ResponseBuild;
import co.com.poli.movieservice.userservice.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingClientFallBackHystrix implements BookingClient {

    private final ResponseBuild builder;

    @Override
    public Response findAllUserIds(Long id) {
        Booking booking = new Booking();
        booking.setUserId(0L);
        booking.setUser(null);
        booking.setShowtimeId(0L);
        booking.setShowtime(null);
        booking.setItems(null);
        return builder.success(booking);
    }

}