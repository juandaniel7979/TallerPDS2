package com.co.poli.showtimeservice.controller;

import com.co.poli.showtimeservice.entity.Showtime;
import com.co.poli.showtimeservice.helpers.ErrorMessage;
import com.co.poli.showtimeservice.helpers.Response;
import com.co.poli.showtimeservice.helpers.ResponseBuild;
import com.co.poli.showtimeservice.services.ShowtimeService;
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
@RequestMapping(value = "/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    private final ResponseBuild responseBuilder;

    @GetMapping
    public Response findAll() {
        List<Showtime> showtime = showtimeService.findAll();
        if (showtime.isEmpty()) {
            return responseBuilder.failed(showtime);
        }
        return responseBuilder.success(showtime);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id ){
        Showtime showtime = showtimeService.findById(id);
        if(showtime == null){
            return responseBuilder.failed(null);
        }
        return responseBuilder.success(showtime);
    }

    @GetMapping("/showtimesId/{movieId}")
    public Response findAllIds(@PathVariable("movieId") Long movieId ) {
        Boolean bookingsIds = showtimeService.listOfIds(movieId);
        return responseBuilder.success(bookingsIds);
    }

    @PostMapping
    public Response save(@Valid @RequestBody Showtime showtime, BindingResult result) {
        if (result.hasErrors()) {
            return responseBuilder.failed(this.formatMessage((result)));
        }
        showtimeService.save(showtime);
        return responseBuilder.success(showtime);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {
        Showtime showtime = showtimeService.findById(id);
        if (showtime == null) {
            return responseBuilder.failed(null);
        }
        showtimeService.delete(showtime);
        return responseBuilder.success(showtime);
    }

    @PutMapping()
    public Response updateStudent(@RequestBody Showtime showtime) {
        return responseBuilder.success(showtimeService.update(showtime));
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