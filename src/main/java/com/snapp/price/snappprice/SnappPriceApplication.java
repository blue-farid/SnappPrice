package com.snapp.price.snappprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.snapp.price.snappprice.repository")
public class SnappPriceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnappPriceApplication.class, args);
    }

}
