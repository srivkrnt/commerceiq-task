package com.commerceiq.scraper.factory;

import com.commerceiq.scraper.constants.AmazonConstants;
import com.commerceiq.scraper.dto.Retailer;
import com.commerceiq.scraper.enums.RetailerEnum;
import com.commerceiq.scraper.selectors.AmazonSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetailerFactory {
    @Autowired
    AmazonSelector amazonSelector;

    public Retailer getRetailer(Byte retailerId) {
        Retailer retailer = new Retailer();
        RetailerEnum retailerType = RetailerEnum.getById(retailerId);
        switch (retailerType) {
            case AMAZON:
                retailer = getAmazonRetailer();
        }

        return retailer;
    }

    public Retailer getAmazonRetailer() {
        return Retailer.builder()
                .id(RetailerEnum.AMAZON.getId())
                .name(RetailerEnum.AMAZON.getName())
                .prefixUrl(AmazonConstants.PREFIX_URL)
                .selector(amazonSelector)
                .build();
    }
}
