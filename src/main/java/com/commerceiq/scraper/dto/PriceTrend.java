package com.commerceiq.scraper.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PriceTrend {
    private String timestamp;
    private String price;
}