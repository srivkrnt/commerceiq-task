package com.commerceiq.scraper.controller;

import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.service.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DetailController {
    @Autowired
    ScrapingService scrapingService;

    @GetMapping("/getHtml")
    public ResponseEntity<?> getHtml(@RequestParam String urlOrSku,
                                     @RequestParam Byte retailerId) throws IOException {
        String htmlResponse = scrapingService.getPageContent(urlOrSku, retailerId);
        return new ResponseEntity<>(htmlResponse, HttpStatus.OK);
    }

    @GetMapping("/getProductDetails")
    public ResponseEntity<?> getProductDetails(@RequestParam String urlOrSku,
                                               @RequestParam Byte retailerId) throws IOException {
        ProductDetailResponseDto productDetails = scrapingService.getProductDetails(urlOrSku, retailerId);
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }
}
