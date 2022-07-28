package co.com.poli.showtimeservice.helpers;

import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Component
public class ResponseBuilder {

    public co.com.poli.showtimeservice.helpers.Response success(){
        return co.com.poli.showtimeservice.helpers.Response.builder()
                .data(OK)
                .status(OK.value())
                .build();
    }

    public co.com.poli.showtimeservice.helpers.Response success(Object data){
        return co.com.poli.showtimeservice.helpers.Response.builder()
                .data(data)
                .status(OK.value())
                .build();
    }

    public co.com.poli.showtimeservice.helpers.Response failed(Object data){
        return co.com.poli.showtimeservice.helpers.Response.builder()
                .data(data)
                .status(INTERNAL_SERVER_ERROR.value())
                .build();
    }


}