package com.snapp.price.snappprice.service;

import com.snapp.price.snappprice.model.BoxPriceResponseDto;
import com.snapp.price.snappprice.model.Location;
import com.snapp.price.snappprice.repository.PricingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {

    private final PricingRepository pricingRepository;
    @Value("${price.ratio}")
    private Double priceRatio;

    @Override
    public BoxPriceResponseDto getBoxPrice(Location origin, Location[] destinations, String orderType) {
        double orderTypeRatio = pricingRepository.findOrderTypeByValue(orderType).getRatio();
        double price = calculateDistance(origin, destinations[0]) * priceRatio * orderTypeRatio;
        for (int i = 0; i < destinations.length - 1; i++) {
            price += calculateDistance(destinations[i], destinations[i + 1]) * priceRatio * orderTypeRatio;
        }
        return new BoxPriceResponseDto(String.valueOf(price));
    }

    private double calculateDistance(Location origin, Location destination) {
        return Math.sqrt(Math.pow(destination.getX() - origin.getX(), 2) + Math.pow(destination.getY() - origin.getY(), 2));
    }
}
