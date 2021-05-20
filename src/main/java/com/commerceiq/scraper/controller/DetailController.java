package com.commerceiq.scraper.controller;

import com.commerceiq.scraper.Application;
import com.commerceiq.scraper.dto.PriceTrendResponseDto;
import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.entity.ProductDetail;
import com.commerceiq.scraper.service.ProductDetailService;
import com.commerceiq.scraper.service.ScrapingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DetailController {
    private static final Logger logger = LoggerFactory.getLogger(DetailController.class);

    @Autowired
    ScrapingService scrapingService;

    @Autowired
    ProductDetailService productDetailService;

    @GetMapping("/get-html")
    public ResponseEntity<?> getHtml(@RequestParam String urlOrSku,
                                     @RequestParam Byte retailerId) throws IOException {
        logger.info("Getting html for retailerId :: {} and urlOrSku :: {}", retailerId, urlOrSku);
        String htmlResponse = scrapingService.getPageContent(urlOrSku, retailerId);
        return new ResponseEntity<>(htmlResponse, HttpStatus.OK);
    }

    @GetMapping("/get-product-details")
    public ResponseEntity<?> getProductDetails(@RequestParam String urlOrSku,
                                               @RequestParam Byte retailerId) throws IOException {
        logger.info("Getting product details for retailerId :: {} and urlOrSku :: {}", retailerId, urlOrSku);
        ProductDetailResponseDto productDetails = scrapingService.getProductDetails(urlOrSku, retailerId);
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @GetMapping("/get-all-product-details")
    private ResponseEntity<?> getAllProductDetails() {
        logger.info("Getting all product details");
        List<ProductDetail> productDetails = productDetailService.getAllProducts();
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @GetMapping("price-trend")
    private ResponseEntity<?> priceTrend(@RequestParam String urlOrSku,
                                         @RequestParam Byte retailerId) throws IOException {
        logger.info("Getting price trend for retailerId :: {} and urlOrSku :: {}", retailerId, urlOrSku);
        PriceTrendResponseDto priceTrend = productDetailService.getPriceTrend(urlOrSku, retailerId);
        return new ResponseEntity<>(priceTrend, HttpStatus.OK);
    }

    @GetMapping("detail-history")
    private ResponseEntity<?> history(@RequestParam String urlOrSku,
                                      @RequestParam Byte retailerId,
                                      @RequestParam Long timeStamp) {
        logger.info("Getting prodcut detail history for retailerId :: {}, urlOrSku :: {}, timestamp :: {} ", retailerId, urlOrSku, timeStamp);
        ProductDetail productDetail = productDetailService.getProductHistory(urlOrSku, retailerId, timeStamp);
        return new ResponseEntity<>(productDetail, HttpStatus.OK);
    }
}
