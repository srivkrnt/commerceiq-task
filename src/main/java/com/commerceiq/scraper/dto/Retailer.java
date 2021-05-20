package com.commerceiq.scraper.dto;

import com.commerceiq.scraper.selectors.Selector;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Retailer {
    private Byte id;
    private String name;
    private Selector selector;
    private String prefixUrl;
}
