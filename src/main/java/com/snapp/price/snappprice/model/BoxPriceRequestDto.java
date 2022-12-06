package com.snapp.price.snappprice.model;

import lombok.Builder;

@Builder
public class BoxPriceRequestDto {
    private Location origin;
    private Location destination;
}
