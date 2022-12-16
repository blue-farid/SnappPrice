package com.snapp.price.snappprice.controller;

import com.snapp.price.snappprice.model.BoxPriceResponseDto;
import com.snapp.price.snappprice.model.Location;
import com.snapp.price.snappprice.repository.PricingRepository;
import com.snapp.price.snappprice.service.PricingService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ActiveProfiles("test")
class PriceControllerTest {

    @MockBean
    PricingService pricingService;

    @MockBean
    PricingRepository pricingRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MessageSource messageSource;

    Locale locale = Locale.ENGLISH;

    @Test
    @SneakyThrows
    void getBoxPrice_ok() {
        // given
        given(pricingService.getBoxPrice(any(Location.class), any(Location[].class), any(String.class))).
                willReturn(BoxPriceResponseDto.builder().priceAmount("40000").build());


        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("/price/box")
                .queryParam("sourceX", "1.0")
                .queryParam("sourceY", "1.0")
                .queryParam("destinationX", List.of("2.0"))
                .queryParam("destinationY", List.of("2.0"))
                .queryParam("orderTypeValue", "BIKE")
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uriComponents.toUriString())
                .accept(MediaType.APPLICATION_JSON);

        // when then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.priceAmount").value("40000"))
                .andExpect(jsonPath("$.message").value(messageSource.getMessage("get.success", null, locale)));
    }
}