package com.chaekibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ChaeKiBackendApplication {

    public static void main(String[] args) {
//        요청 -> 응답(책)
        SpringApplication.run(ChaeKiBackendApplication.class, args);
    }
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
}
