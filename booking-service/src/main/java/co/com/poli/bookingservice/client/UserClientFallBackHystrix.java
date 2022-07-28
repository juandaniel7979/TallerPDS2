package co.com.poli.bookingservice.client;

import co.com.poli.bookingservice.helpers.Response;
import co.com.poli.bookingservice.helpers.ResponseBuild;
import co.com.poli.bookingservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClientFallBackHystrix implements co.com.poli.bookingservice.client.UserClient {

    private final ResponseBuild builder;

    @Override
    public Response findById(Long id) {
        User user = new User();
        user.setName("");
        user.setLastName("");
        return builder.success(user);
    }

}