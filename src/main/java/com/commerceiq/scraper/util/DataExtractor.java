package com.commerceiq.scraper.util;

import com.commerceiq.scraper.constants.AmazonConstants;
import com.commerceiq.scraper.constants.LabelConstants;
import com.commerceiq.scraper.dto.Retailer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class DataExtractor {

    public String getTitle(Document document, Retailer retailer) {
        Element title = getContentUsingSelector(document, retailer.getSelector().getTitleSelector());
        return handleIfNotAvailable(title);
    }

    public String getOfferPrice(Document document , Retailer retailer) {
        Element offerPrice = getContentUsingSelector(document, retailer.getSelector().getOfferPriceSelector());
        return handleIfNotAvailable(offerPrice);
    }

    public String getDescription(Document document, Retailer retailer) {
        Element description = getContentUsingSelector(document, retailer.getSelector().getDescriptionSelector());
        return handleIfNotAvailable(description);
    }

    public Map<String, String> getRatingMap(Document document, Retailer retailer) {
        Map<String, String> ratingsMap = new HashMap<>();
        Element totalRating = getContentUsingSelector(document, retailer.getSelector().getTotalRatingSelector());
        String overallCount = getOverallCount(totalRating);
        ratingsMap.put(LabelConstants.OVERALL_COUNT, overallCount);

        Integer ratingIndex = 1;
        for(String rating: LabelConstants.RATINGS) {
            Element ratingValue = getContentUsingSelector(document, getRatingSelectorByIndex(ratingIndex, retailer));
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

    private String getRatingSelectorByIndex(Integer index, Retailer retailer) {
        return new StringBuilder()
                .append(retailer.getSelector().getRatingSelector())
                .append("(")
                .append(index)
                .append(")")
                .append(retailer.getSelector().getRatingPercentageSelector())
                .toString();
    }

    private String getOverallCount(Element totalRating) {
        String rating = handleIfNotAvailable(totalRating);
        String[] splitRating = rating.split(" ");
        if(splitRating.length == 0) return null;
        return splitRating[0];
    }
}
