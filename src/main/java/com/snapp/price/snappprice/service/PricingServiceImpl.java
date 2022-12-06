package com.snapp.price.snappprice.service;

import com.snapp.price.snappprice.model.BoxPriceResponseDto;
import com.snapp.price.snappprice.model.Location;

public class PricingServiceImpl implements PricingService {
    @Override
    public BoxPriceResponseDto getBoxPrice(Location origin, Location destination) {
        double distance = Math.sqrt(Math.pow(destination.getX() - origin.getX(), 2) + Math.pow(destination.getY() - origin.getY(), 2));
        double price = distance * 10000;
        return new BoxPriceResponseDto(String.valueOf(price));
    }
}
