package com.commerceiq.scraper.constants;

public class AmazonConstants {
    public static final String PREFIX_URL = "https://www.amazon.in/dp";
    public static final String TITLE_SELECTOR = "#productTitle";
    public static final String OFFER_PRICE_SELECTOR = "#priceblock_ourprice";
    public static final String DESCRIPTION_SELECTOR = "#productDescription > p:nth-child(2)";
    public static final String TOTAL_RATING_SELECTOR = ".averageStarRatingNumerical > span:nth-child(1)";
    public static final String RATING_SELECTOR = "tr.a-histogram-row:nth-child";
    public static final String RATING_PERCENTAGE_SELECTOR = "> td:nth-child(3) > span:nth-child(2) > a:nth-child(1)";
}