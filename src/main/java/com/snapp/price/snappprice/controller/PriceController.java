package com.snapp.price.snappprice.controller;

import com.snapp.price.snappprice.model.BaseResponseDto;
import com.snapp.price.snappprice.model.BoxPriceResponseDto;
import com.snapp.price.snappprice.model.Location;
import com.snapp.price.snappprice.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/price")
public class PriceController {

    private final PricingService priceService;
    private final MessageSource source;

    @PostMapping("/box")
    public ResponseEntity<BaseResponseDto<BoxPriceResponseDto>> getBoxPrice(@RequestParam Long sourceX
            , @RequestParam Long sourceY
            , @RequestParam Long[] destinationX
            , @RequestParam Long[] destinationY
            , Locale locale) {
        Location origin = new Location(sourceX, sourceY);
        Location[] destinations = new Location[destinationX.length];
        // create locations array
        for (int i = 0; i < destinationX.length; i++) {
            Location destination = new Location(destinationX[i], destinationY[i]);
            destinations[i] = destination;
        }

        BoxPriceResponseDto dto = priceService.getBoxPrice(origin, destinations);
        return ResponseEntity.ok().body(BaseResponseDto.<BoxPriceResponseDto>builder().result(dto).message(
                source.getMessage("get.success", null, locale)
        ).build());
    }
}
