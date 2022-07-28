package com.co.poli.showtimeservice.helpers;

import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Component
public class ResponseBuild {

    public com.co.poli.showtimeservice.helpers.Response success(){
        return com.co.poli.showtimeservice.helpers.Response.builder()
                .data(OK)
                .status(OK.value())
                .build();
    }

    public com.co.poli.showtimeservice.helpers.Response success(Object data){
        return com.co.poli.showtimeservice.helpers.Response.builder()
                .data(data)
                .status(OK.value())
                .build();
    }

    public com.co.poli.showtimeservice.helpers.Response failed(Object data){
        return com.co.poli.showtimeservice.helpers.Response.builder()
                .data(data)
                .status(INTERNAL_SERVER_ERROR.value())
                .build();
    }


}