package com.commerceiq.scraper.util;

import com.commerceiq.scraper.constants.LabelConstants;
import com.commerceiq.scraper.dto.Retailer;
import com.commerceiq.scraper.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlUtil {

    @Autowired
    RetailerService retailerService;

    public String formUrl(String urlOrSku, Byte retailerId) {
        return  isUrl(urlOrSku) ? urlOrSku : formUrlUsingSKU(urlOrSku, retailerId);
    }

    private String formUrlUsingSKU(String sku, Byte retailerId) {
        String prefixUrl = retailerService.getPrefixUrl(retailerId);
        return new StringBuilder()
                .append(prefixUrl)
                .append("/")
                .append(sku)
                .toString();
    }

    private Boolean isUrl(String urlOrSku){
        return urlOrSku.toLowerCase().contains(LabelConstants.HTTPS.toLowerCase());
    }

}
