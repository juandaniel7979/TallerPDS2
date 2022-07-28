package com.co.poli.showtimeservice.client;

import com.co.poli.showtimeservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service",fallback = MovieClientFallBackHystrix.class)
public interface MovieClient {

    @GetMapping("/movies/{id}")
    Response findById(@PathVariable("id") Long id );
}
