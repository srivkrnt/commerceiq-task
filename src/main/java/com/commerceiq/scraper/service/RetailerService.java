package com.commerceiq.scraper.service;

import com.commerceiq.scraper.selectors.Selector;

public interface RetailerService {
    String getPrefixUrl(Byte retailerId);
    Selector getSelector(Byte retailerId);
}
