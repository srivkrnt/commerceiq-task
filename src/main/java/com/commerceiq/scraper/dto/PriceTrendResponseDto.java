package com.commerceiq.scraper.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceTrendResponseDto {
    private String url;
    private List<PriceTrend> prices;
}
