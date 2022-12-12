package com.snapp.price.snappprice;

import com.snapp.price.snappprice.service.PricingService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class EndToEndTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PricingService pricingService;

    @Autowired
    MessageSource messageSource;

    Locale locale = Locale.ENGLISH;

    @Test
    @SneakyThrows
    void givenLocations_whenGetBoxPrice_thenOk() {
        // given
        Double sourceX = 1.0;
        Double sourceY = 1.0;
        Double[] destinationX = new Double[]{2.0};
        Double[] destinationY = new Double[]{2.0};

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("/price/box")
                .queryParam("sourceX", sourceX)
                .queryParam("sourceY", sourceY)
                .queryParam("destinationX", (Object[]) destinationX)
                .queryParam("destinationY", (Object[]) destinationY)
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uriComponents.toUriString())
                .accept(MediaType.APPLICATION_JSON);

        // when then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.priceAmount").value("14142.135623730952"))
                .andExpect(jsonPath("$.message").value(messageSource.getMessage("get.success", null, locale)));
    }
}
