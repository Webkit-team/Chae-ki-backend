package com.chaekibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableScheduling
public class ChaeKiBackendApplication {

    public static void main(String[] args) {
//        요청 -> 응답(책)
        SpringApplication.run(ChaeKiBackendApplication.class, args);
    }
}
