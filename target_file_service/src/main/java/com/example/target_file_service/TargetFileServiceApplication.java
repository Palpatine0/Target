package com.example.target_file_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TargetFileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TargetFileServiceApplication.class, args);
    }

}
