package com.commerceiq.scraper.db;
import com.commerceiq.scraper.entity.ProductDetail;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class InMemDb {
    private Long productDetailId;
    private List<ProductDetail> productDetails;

    InMemDb() {
        productDetailId = 0L;
        productDetails = new ArrayList<>();
    }

    public ProductDetail addProductDetail(ProductDetail productDetail) {
        Long currentId = productDetailId + 1;
        productDetailId += 1;
        productDetail.setId(currentId);
        productDetails.add(productDetail);
        return productDetail;
    }

    public List<ProductDetail> getProductDetailsByUrl(String url) {
        List<ProductDetail> found = new ArrayList<>();
        for(ProductDetail productDetail: productDetails) {
            if(url.equals(productDetail.getUrl())) {
                found.add(productDetail);
            }
        }

        return found;
    }
}
