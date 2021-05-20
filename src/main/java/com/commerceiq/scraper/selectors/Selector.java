package com.commerceiq.scraper.selectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Selector {
    private String titleSelector;
    private String descriptionSelector;
    private String offerPriceSelector;
    private String totalRatingSelector;
    private String ratingSelector;
    private String ratingPercentageSelector;
}
