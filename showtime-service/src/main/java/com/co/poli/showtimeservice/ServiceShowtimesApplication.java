package com.co.poli.showtimeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceShowtimesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceShowtimesApplication.class, args);
    }
}
