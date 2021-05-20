package com.commerceiq.scraper.builder;

import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.dto.Retailer;
import com.commerceiq.scraper.factory.RetailerFactory;
import com.commerceiq.scraper.selectors.Selector;
import com.commerceiq.scraper.service.RetailerService;
import com.commerceiq.scraper.service.impl.ScrapingServiceImpl;
import com.commerceiq.scraper.util.DataExtractor;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailResponseBuilder {

    private static final Logger logger = LoggerFactory.getLogger(DetailResponseBuilder.class);

    @Autowired
    DataExtractor dataExtractor;

    @Autowired
    RetailerFactory retailerFactory;

    @Autowired
    RetailerService retailerService;

    public ProductDetailResponseDto build(Document document, Byte retailerId) {
        logger.info("building product details response dto for retailer Id :: {}", retailerId);
        Selector selector = retailerService.getSelector(retailerId);

        return ProductDetailResponseDto.builder()
                .title(dataExtractor.getTitle(document, selector))
                .offerPrice(dataExtractor.getOfferPrice(document, selector))
                .description(dataExtractor.getDescription(document, selector))
                .ratingsMap(dataExtractor.getRatingMap(document, selector))
                .build();
    }
}
