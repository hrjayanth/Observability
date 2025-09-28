package com.learn.jay.java_simple;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DiceApplication.class);
//                SpringApplication.run(DiceApplication.class, args);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}