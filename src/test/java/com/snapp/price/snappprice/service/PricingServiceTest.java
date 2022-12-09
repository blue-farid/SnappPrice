package com.snapp.price.snappprice.service;

import com.snapp.price.snappprice.model.BoxPriceResponseDto;
import com.snapp.price.snappprice.model.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PricingServiceTest {

    @Autowired
    PricingService pricingService;

    @Test
    void getBoxPrice_ok() {
        // given
        Location origin = new Location(1.0, 1.0);
        Location[] destinations = new Location[]{new Location(2.0, 2.0)};
        // when
        BoxPriceResponseDto price = pricingService.getBoxPrice(origin, destinations);
        // then
        Assertions.assertEquals("14142.135623730952", price.getPriceAmount());
    }
}