package com.commerceiq.scraper.util;

import com.commerceiq.scraper.constants.AmazonConstants;
import com.commerceiq.scraper.constants.LabelConstants;
import com.commerceiq.scraper.dto.Retailer;
import com.commerceiq.scraper.selectors.Selector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class DataExtractor {

    public String getTitle(Document document, Selector selector) {
        Element title = getContentUsingSelector(document, selector.getTitleSelector());
        return handleIfNotAvailable(title);
    }

    public String getOfferPrice(Document document , Selector selector) {
        Element offerPrice = getContentUsingSelector(document, selector.getOfferPriceSelector());
        return handleIfNotAvailable(offerPrice);
    }

    public String getDescription(Document document, Selector selector) {
        Element description = getContentUsingSelector(document, selector.getDescriptionSelector());
        return handleIfNotAvailable(description);
    }

    public Map<String, String> getRatingMap(Document document, Selector selector) {
        Map<String, String> ratingsMap = new HashMap<>();
        Element totalRating = getContentUsingSelector(document, selector.getTotalRatingSelector());
        String overallCount = getOverallCount(totalRating);
        ratingsMap.put(LabelConstants.OVERALL_COUNT, overallCount);

        Integer ratingIndex = 1;
        for(String rating: LabelConstants.RATINGS) {
            Element ratingValue = getContentUsingSelector(document, getRatingSelectorByIndex(ratingIndex, selector));
            ratingsMap.put(rating, handleIfNotAvailable(ratingValue));
            ratingIndex += 1;
        }
        return ratingsMap;
    }

    private Element getContentUsingSelector(Document document, String cssSelector) {
        return document.select(cssSelector).first();
    }


    private String handleIfNotAvailable(Element element) {
        return Objects.isNull(element) ? LabelConstants.NA : element.text();
    }

    private String getRatingSelectorByIndex(Integer index, Selector selector) {
        return new StringBuilder()
                .append(selector.getRatingSelector())
                .append("(")
                .append(index)
                .append(")")
                .append(selector.getRatingPercentageSelector())
                .toString();
    }

    private String getOverallCount(Element totalRating) {
        String rating = handleIfNotAvailable(totalRating);
        String[] splitRating = rating.split(" ");
        if(splitRating.length == 0) return null;
        return splitRating[0];
    }
}
