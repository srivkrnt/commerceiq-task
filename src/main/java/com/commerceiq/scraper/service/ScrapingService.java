package com.commerceiq.scraper.service;

import com.commerceiq.scraper.dto.ProductDetailResponseDto;

import java.io.IOException;

public interface ScrapingService {
    String getPageContent(String url, Byte retailerId) throws IOException;

    ProductDetailResponseDto getProductDetails(String urlOrSku, Byte retailerId) throws IOException;
}
