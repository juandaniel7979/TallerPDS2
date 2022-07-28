package co.com.poli.bookingservice.client;

import co.com.poli.bookingservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "showtime-service", fallback = ShowtimeClientFallBackHystrix.class)
public interface ShowtimeClient {

    @GetMapping("/showtimes/{id}")
    Response findById(@PathVariable("id") Long id );
}
