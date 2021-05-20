package com.commerceiq.scraper.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ProductDetail extends AuditColumns{
    private Long id;
    private String url;
    private String title;
    private String offerPrice;
    private String description;
    private String ratingsMap;
}
