package com.commerceiq.scraper.service.impl;

import com.commerceiq.scraper.db.InMemDb;
import com.commerceiq.scraper.dto.PriceTrend;
import com.commerceiq.scraper.dto.PriceTrendResponseDto;
import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.entity.ProductDetail;
import com.commerceiq.scraper.mapper.ProductDetailMapper;
import com.commerceiq.scraper.service.ProductDetailService;
import com.commerceiq.scraper.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    private static final Logger logger = LoggerFactory.getLogger(ProductDetailServiceImpl.class);

    @Autowired
    InMemDb db;

    @Autowired
    UrlUtil urlUtil;

    @Autowired
    ProductDetailMapper productDetailMapper;

    @Override
    public void createEntry(ProductDetailResponseDto productDetailResponse, String url) {
        logger.info("Creating product entry with data :: {}", productDetailResponse.toString());
        ProductDetail productDetail = productDetailMapper.map(productDetailResponse, url);
        db.addProductDetail(productDetail);
    }

    @Override
    public List<ProductDetail> getAllProducts() {
        return db.getProductDetails();
    }

    @Override
    public PriceTrendResponseDto getPriceTrend(String urlOrSku, Byte retailerId) {
        String url = urlUtil.formUrl(urlOrSku, retailerId);
        logger.info("Getting price trend for url :: {}", url);
        List<ProductDetail> productDetails = db.getProductDetailsByUrl(url);
        PriceTrendResponseDto priceTrend = buildPriceTrendDto(productDetails, url);
        return priceTrend;
    }

    @Override
    public ProductDetail getProductHistory(String urlOrSku, Byte retailerId, Long timestamp) {
        String url = urlUtil.formUrl(urlOrSku, retailerId);
        ProductDetail lastProductDetail = new ProductDetail();
        List<ProductDetail> productDetails = db.getProductDetailsByUrl(url);
        logger.info("Got product details from db for url :: {} as {}", url, productDetails.toString());
        for(ProductDetail productDetail: productDetails) {
            if(productDetail.getCreatedAt().getTime() >= timestamp) {
                break;
            }
            else {
                lastProductDetail = productDetail;
            }
        }
        return lastProductDetail;
    }

    private PriceTrendResponseDto buildPriceTrendDto(List<ProductDetail> productDetails, String url) {
        logger.info("Building price trend DTO for url :: {}", url);
        List<PriceTrend> priceTrends = new ArrayList<>();
        for(ProductDetail productDetail: productDetails) {
            PriceTrend priceTrend = PriceTrend.builder()
                    .price(productDetail.getOfferPrice())
                    .timestamp(productDetail.getCreatedAt().toString())
                    .build();
            priceTrends.add(priceTrend);
        }

        return PriceTrendResponseDto.builder()
                .url(url)
                .prices(priceTrends)
                .build();
    }
}
