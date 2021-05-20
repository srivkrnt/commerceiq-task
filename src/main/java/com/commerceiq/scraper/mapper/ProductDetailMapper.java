package com.commerceiq.scraper.mapper;

import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.entity.ProductDetail;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductDetailMapper {
    public ProductDetail map(ProductDetailResponseDto productDetailResponseDto, String url) {
        return ProductDetail.builder()
                .url(url)
                .offerPrice(productDetailResponseDto.getOfferPrice())
                .description(productDetailResponseDto.getDescription())
                .ratingsMap(productDetailResponseDto.getRatingsMap().toString())
                .title(productDetailResponseDto.getTitle())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }
}
