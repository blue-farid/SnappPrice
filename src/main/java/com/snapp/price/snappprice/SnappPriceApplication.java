package com.snapp.price.snappprice;

import com.snapp.price.snappprice.model.entity.OrderType;
import com.snapp.price.snappprice.repository.PricingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.snapp.price.snappprice.repository")
@RequiredArgsConstructor
public class SnappPriceApplication implements CommandLineRunner {

    private final PricingRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SnappPriceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        OrderType type = new OrderType();
        type.setValue("BIKE");
        type.setRatio(1.5);
        OrderType type2 = new OrderType();
        type2.setValue("CAR");
        type2.setRatio(2.0);
        repository.save(type);
        repository.save(type2);
    }
}
