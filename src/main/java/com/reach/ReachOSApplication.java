package com.reach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@EnableWebMvc
@EnableSwagger2
public class ReachOSApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReachOSApplication.class, args);
    }
}
