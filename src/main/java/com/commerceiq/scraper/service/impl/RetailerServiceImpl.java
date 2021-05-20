package com.commerceiq.scraper.service.impl;

import com.commerceiq.scraper.dto.Retailer;
import com.commerceiq.scraper.enums.RetailerEnum;
import com.commerceiq.scraper.factory.RetailerFactory;
import com.commerceiq.scraper.selectors.Selector;
import com.commerceiq.scraper.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetailerServiceImpl implements RetailerService {

    @Autowired
    RetailerFactory retailerFactory;

    @Override
    public String getPrefixUrl(Byte retailerId) {
        Retailer retailer = retailerFactory.getRetailer(retailerId);
        return retailer.getPrefixUrl();
    }

    @Override
    public Selector getSelector(Byte retailerId) {
        Retailer retailer = retailerFactory.getRetailer(retailerId);
        return retailer.getSelector();
    }
}
