package com.leonardolelli.ReviewService;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ReviewServiceApplication {

    public static void main(String[] args) {
	SpringApplication.run(ReviewServiceApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
	return new RestTemplateBuilder().setReadTimeout(Duration.ofMinutes(1)).build();
    }

}
