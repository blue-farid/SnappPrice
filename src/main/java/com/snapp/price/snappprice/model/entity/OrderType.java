package com.snapp.price.snappprice.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "orderTypes")
@Getter
@Setter
public class OrderType {
    @Id
    private String id;
    private String value;
    private Double ratio;
}
