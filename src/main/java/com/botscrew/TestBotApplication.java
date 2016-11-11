package com.botscrew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.botscrew")
@SpringBootApplication
public class TestBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestBotApplication.class, args);
    }
}
