package com.commerceiq.scraper.service.impl;

import com.commerceiq.scraper.builder.DetailResponseBuilder;
import com.commerceiq.scraper.dto.ProductDetailResponseDto;
import com.commerceiq.scraper.service.ScrapingService;
import com.commerceiq.scraper.util.UrlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScrapingServiceImpl implements ScrapingService {
    @Autowired
    UrlUtil urlUtil;

    @Autowired
    DetailResponseBuilder detailResponseBuilder;

    @Override
    public String getPageContent(String urlOrSku, Byte retailerId) throws IOException {
        String url = urlUtil.formUrl(urlOrSku, retailerId);
        Document pageHtml = getPageHtml(url);
        return pageHtml.toString();
    }

    @Override
    public ProductDetailResponseDto getProductDetails(String urlOrSku, Byte retailerId) throws IOException {
        String url = urlUtil.formUrl(urlOrSku, retailerId);
        Document pageHtml = getPageHtml(url);
        return detailResponseBuilder.build(pageHtml, retailerId);
    }

    private Document getPageHtml(String url) throws IOException {
        Document pageContent = Jsoup.connect(url).get();
        return pageContent;
    }
}
