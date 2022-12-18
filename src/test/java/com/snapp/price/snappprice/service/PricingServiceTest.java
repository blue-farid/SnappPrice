package com.snapp.price.snappprice.service;

import com.snapp.price.snappprice.model.BoxPriceResponseDto;
import com.snapp.price.snappprice.model.Location;
import com.snapp.price.snappprice.model.entity.OrderType;
import com.snapp.price.snappprice.repository.PricingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class PricingServiceTest {

    @Autowired
    PricingService pricingService;

    @MockBean
    PricingRepository pricingRepository;

    @Test
    void getBoxPrice_ok() {
        // given
        given(pricingRepository.findOrderTypeByValue("BIKE")).willReturn(OrderType.builder().id("id")
                .value("BIKE").ratio(1.5).build());

        Location origin = new Location(1.0, 1.0);
        Location[] destinations = new Location[]{new Location(2.0, 2.0)};
        // when
        BoxPriceResponseDto price = pricingService.getBoxPrice(origin, destinations, "BIKE");
        // then
        Assertions.assertEquals("21213.203435596428", price.getPriceAmount());
    }
}