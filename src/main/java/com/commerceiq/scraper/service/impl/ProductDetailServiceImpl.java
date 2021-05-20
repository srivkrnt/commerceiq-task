package com.commerceiq.scraper.service.impl;

import com.commerceiq.scraper.db.InMemDb;
import com.commerceiq.scraper.dto.PriceTrend;
import com.commerceiq.scraper.dto.PriceTrendResponseDto;
import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.entity.ProductDetail;
import com.commerceiq.scraper.mapper.ProductDetailMapper;
import com.commerceiq.scraper.service.ProductDetailService;
import com.commerceiq.scraper.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    InMemDb db;

    @Autowired
    UrlUtil urlUtil;

    @Autowired
    ProductDetailMapper productDetailMapper;

    @Override
    public void createEntry(ProductDetailResponseDto productDetailResponse, String url) {
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
        List<ProductDetail> productDetails = db.getProductDetailsByUrl(url);
        PriceTrendResponseDto priceTrend = buildPriceTrendDto(productDetails, url);
        return priceTrend;
    }

    @Override
    public ProductDetail getProductHistory(String urlOrSku, Byte retailerId, Long timestamp) {
        String url = urlUtil.formUrl(urlOrSku, retailerId);
        ProductDetail lastProductDetail = new ProductDetail();
        List<ProductDetail> productDetails = db.getProductDetailsByUrl(url);
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
