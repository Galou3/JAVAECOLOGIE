package org.forest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.forest"})
public class ForestApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ForestApplication.class, args);
    }
}