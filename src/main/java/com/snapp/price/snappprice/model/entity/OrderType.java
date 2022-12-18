package com.snapp.price.snappprice.model.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderTypes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderType {
    @Id
    private String id;
    private String value;
    private Double ratio;
}
