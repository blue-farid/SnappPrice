package com.snapp.price.snappprice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class BoxPriceResponseDto implements Serializable {
    private String priceAmount;
}
