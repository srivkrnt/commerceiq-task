package com.commerceiq.scraper.service;

import com.commerceiq.scraper.dto.PriceTrendResponseDto;
import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.entity.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    void createEntry(ProductDetailResponseDto productDetailResponse, String url);
    List<ProductDetail> getAllProducts();
    PriceTrendResponseDto getPriceTrend(String urlOrSku, Byte retailerId);
    ProductDetail getProductHistory(String urlOrSku, Byte retailerId, Long timestamp);
}
