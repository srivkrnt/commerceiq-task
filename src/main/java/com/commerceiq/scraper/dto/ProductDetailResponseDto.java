package com.commerceiq.scraper.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailResponseDto {
    private String title;
    private String offerPrice;
    private String description;
    private Map<String, String> ratingsMap;
}
