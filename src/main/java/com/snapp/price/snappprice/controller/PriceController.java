package com.snapp.price.snappprice.controller;

import com.snapp.price.snappprice.model.BaseResponseDto;
import com.snapp.price.snappprice.model.BoxPriceResponseDto;
import com.snapp.price.snappprice.model.Location;
import com.snapp.price.snappprice.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class PriceController {

    private final PricingService priceService;
    private final MessageSource source;

    @GetMapping
    public ResponseEntity<BaseResponseDto<BoxPriceResponseDto>> getBoxPrice(@RequestParam Long sourceX
            , @RequestParam Long sourceY
            , @RequestParam Long destinationX
            , @RequestParam Long destinationY
            , Locale locale) {
        Location origin = new Location(sourceX, sourceY);
        Location destination = new Location(destinationX, destinationY);
        BoxPriceResponseDto dto = priceService.getBoxPrice(origin, destination);
        return ResponseEntity.ok().body(BaseResponseDto.<BoxPriceResponseDto>builder().result(dto).message(
                source.getMessage("get.success", null, locale)
        ).build());
    }
}
