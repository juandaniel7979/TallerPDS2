package co.com.poli.bookingservice.helpers;

import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Component
public class ResponseBuild {

    public co.com.poli.bookingservice.helpers.Response success(){
        return co.com.poli.bookingservice.helpers.Response.builder()
                .data(OK)
                .status(OK.value())
                .build();
    }

    public co.com.poli.bookingservice.helpers.Response success(Object data){
        return co.com.poli.bookingservice.helpers.Response.builder()
                .data(data)
                .status(OK.value())
                .build();
    }

    public co.com.poli.bookingservice.helpers.Response failed(Object data){
        return co.com.poli.bookingservice.helpers.Response.builder()
                .data(data)
                .status(INTERNAL_SERVER_ERROR.value())
                .build();
    }


}

