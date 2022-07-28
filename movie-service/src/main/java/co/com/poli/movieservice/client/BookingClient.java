package co.com.poli.movieservice.client;

import co.com.poli.movieservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service", fallback = BookingClientFallBackHystrix.class)
public interface BookingClient {
    @GetMapping("/bookings/bookingsId/{movieId}")
    Response findAllIds(@PathVariable("movieId") Long movieId );
}
