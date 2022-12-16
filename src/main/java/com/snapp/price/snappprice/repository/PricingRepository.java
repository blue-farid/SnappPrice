package com.snapp.price.snappprice.repository;

import com.snapp.price.snappprice.model.entity.OrderType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends MongoRepository<OrderType, String> {
    OrderType findOrderTypeByValue(String value);
}
