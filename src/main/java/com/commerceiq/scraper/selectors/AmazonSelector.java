package com.commerceiq.scraper.selectors;

import com.commerceiq.scraper.constants.AmazonConstants;
import org.springframework.stereotype.Component;

@Component
public class AmazonSelector extends Selector {
    AmazonSelector() {
        this.setTitleSelector(AmazonConstants.TITLE_SELECTOR);
        this.setOfferPriceSelector(AmazonConstants.OFFER_PRICE_SELECTOR);
        this.setDescriptionSelector(AmazonConstants.DESCRIPTION_SELECTOR);
        this.setRatingSelector(AmazonConstants.RATING_SELECTOR);
        this.setRatingPercentageSelector(AmazonConstants.RATING_PERCENTAGE_SELECTOR);
        this.setTotalRatingSelector(AmazonConstants.TOTAL_RATING_SELECTOR);
    }
}
