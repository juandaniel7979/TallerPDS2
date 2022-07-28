package com.co.poli.showtimeservice.helpers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private Object data;
    private Integer status;
}
