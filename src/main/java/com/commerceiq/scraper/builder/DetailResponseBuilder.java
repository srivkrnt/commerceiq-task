package com.commerceiq.scraper.builder;

import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.dto.Retailer;
import com.commerceiq.scraper.factory.RetailerFactory;
import com.commerceiq.scraper.util.DataExtractor;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailResponseBuilder {
    @Autowired
    DataExtractor dataExtractor;

    @Autowired
    RetailerFactory retailerFactory;

    public ProductDetailResponseDto build(Document document, Byte retailerId) {
        Retailer retailer = retailerFactory.getRetailer(retailerId);
        return ProductDetailResponseDto.builder()
                .title(dataExtractor.getTitle(document, retailer))
                .offerPrice(dataExtractor.getOfferPrice(document, retailer))
                .description(dataExtractor.getDescription(document, retailer))
                .ratingsMap(dataExtractor.getRatingMap(document, retailer))
                .build();
    }
}
