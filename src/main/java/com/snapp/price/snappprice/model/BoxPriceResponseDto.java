package com.snapp.price.snappprice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class BoxPriceResponseDto implements Serializable {
    private String priceAmount;
}
