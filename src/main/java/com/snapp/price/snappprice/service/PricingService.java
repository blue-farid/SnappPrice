package com.snapp.price.snappprice.service;

import com.snapp.price.snappprice.model.BoxPriceResponseDto;
import com.snapp.price.snappprice.model.Location;

public interface PricingService {
    BoxPriceResponseDto getBoxPrice(Location origin, Location[] destination, String orderType);
}
