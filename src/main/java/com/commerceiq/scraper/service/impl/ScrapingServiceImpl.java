package com.commerceiq.scraper.service.impl;

import com.commerceiq.scraper.builder.DetailResponseBuilder;
import com.commerceiq.scraper.controller.DetailController;
import com.commerceiq.scraper.db.InMemDb;
import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.entity.ProductDetail;
import com.commerceiq.scraper.service.ProductDetailService;
import com.commerceiq.scraper.service.ScrapingService;
import com.commerceiq.scraper.util.UrlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.List;

@Service
public class ScrapingServiceImpl implements ScrapingService {

    private static final Logger logger = LoggerFactory.getLogger(ScrapingServiceImpl.class);

    @Autowired
    UrlUtil urlUtil;

    @Autowired
    DetailResponseBuilder detailResponseBuilder;

    @Autowired
    ProductDetailService productDetailService;

    @Autowired
    InMemDb inMemDb;

    @Override
    public String getPageContent(String urlOrSku, Byte retailerId) throws IOException {
        String url = urlUtil.formUrl(urlOrSku, retailerId);
        logger.info("Formed Url :: {}", url);
        Document pageHtml = getPageHtml(url);
        return pageHtml.toString();
    }

    @Override
    public ProductDetailResponseDto getProductDetails(String urlOrSku, Byte retailerId) throws IOException {
        String url = urlUtil.formUrl(urlOrSku, retailerId);
        logger.info("Formed Url :: {}", url);
        try {
            Document pageHtml = getPageHtml(url);
            ProductDetailResponseDto productDetailResponseDto = detailResponseBuilder.build(pageHtml, retailerId);
            productDetailService.createEntry(productDetailResponseDto, url);
            return productDetailResponseDto;
        }catch (Exception exception){
            logger.error("Failed to get product details :: {}", exception.toString());
            return null;
        }
    }

    private Document getPageHtml(String url) throws IOException {
        Document pageContent = Jsoup.connect(url).get();
        return pageContent;
    }

}
