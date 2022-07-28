package co.com.poli.movieservice.userservice.controller;

import co.com.poli.movieservice.userservice.entity.User;
import co.com.poli.movieservice.userservice.helpers.ErrorMessage;
import co.com.poli.movieservice.userservice.helpers.Response;
import co.com.poli.movieservice.userservice.helpers.ResponseBuilder;
import co.com.poli.movieservice.userservice.services.UserService;
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
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseBuilder responseBuilder;

    @GetMapping
    public Response findAll() {
        List<User> user = userService.findAll();
        if (user.isEmpty()) {
            return responseBuilder.failed(user);
        }
        return responseBuilder.success(user);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id ){
        User user = userService.findById(id);
        if(user == null){
            return responseBuilder.failed(null);
        }
        return responseBuilder.success(user);
    }

    @PostMapping
    public Response save(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return responseBuilder.failed(this.formatMessage((result)));
        }
        userService.save(user);
        return responseBuilder.success(user);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return responseBuilder.failed("no existe el user");
        }
        if (userService.delete(user)) {
            return responseBuilder.success("deleted user");
        } else {
            return responseBuilder.failed("Booking associate");
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