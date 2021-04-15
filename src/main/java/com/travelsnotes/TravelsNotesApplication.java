package com.travelsnotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class TravelsNotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelsNotesApplication.class, args);
    }

}
