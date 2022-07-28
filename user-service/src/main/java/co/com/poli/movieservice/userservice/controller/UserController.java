package co.com.poli.movieservice.userservice.controller;

import co.com.poli.movieservice.userservice.entity.User;
import co.com.poli.movieservice.userservice.helpers.ErrorMessage;
import co.com.poli.movieservice.userservice.helpers.Response;
import co.com.poli.movieservice.userservice.helpers.ResponseBuild;
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
    private final ResponseBuild responseBuild;

    @GetMapping
    public Response findAll() {
        List<User> user = userService.findAll();
        if (user.isEmpty()) {
            return responseBuild.failed(user);
        }
        return responseBuild.success(user);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id ){
        User user = userService.findById(id);
        if(user == null){
            return responseBuild.failed(null);
        }
        return responseBuild.success(user);
    }

    @PostMapping
    public Response save(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return responseBuild.failed(this.formatMessage((result)));
        }
        userService.save(user);
        return responseBuild.success(user);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return responseBuild.failed("no existe el user");
        }
        if (userService.delete(user)) {
            return responseBuild.success("deleted user");
        } else {
            return responseBuild.failed("Booking associate");
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