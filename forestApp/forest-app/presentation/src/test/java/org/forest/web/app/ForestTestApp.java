package org.forest.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.forest"})
public class ForestTestApp {
    public static void main(String[] args) {
        SpringApplication.run(ForestTestApp.class, args);
    }
}
