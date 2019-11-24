package org.restbucks.shopping.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan("org.restbucks.shopping")
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
