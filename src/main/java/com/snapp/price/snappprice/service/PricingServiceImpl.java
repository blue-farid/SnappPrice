package com.snapp.price.snappprice.service;

import com.snapp.price.snappprice.model.BoxPriceResponseDto;
import com.snapp.price.snappprice.model.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceImpl implements PricingService {

    @Value("${price.ratio}")
    private Double priceRatio;

    @Override
    public BoxPriceResponseDto getBoxPrice(Location origin, Location[] destinations) {
        double price = calculateDistance(origin, destinations[0]) * priceRatio;
        for (int i = 0; i < destinations.length - 1; i++) {
            price += calculateDistance(destinations[i], destinations[i + 1]) * priceRatio;
        }
        return new BoxPriceResponseDto(String.valueOf(price));
    }

    private double calculateDistance(Location origin, Location destination) {
        return Math.sqrt(Math.pow(destination.getX() - origin.getX(), 2) + Math.pow(destination.getY() - origin.getY(), 2));
    }
}
