package com.aurorasoft.notificator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableConfigurationProperties
public class ApplicationRunner {

    public static void main(String... args) {
        run(ApplicationRunner.class, args);
    }
}
