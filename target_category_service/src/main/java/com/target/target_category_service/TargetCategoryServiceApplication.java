package com.target.target_category_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.target.target_category_service.mapper")
public class TargetCategoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TargetCategoryServiceApplication.class, args);
    }

}
