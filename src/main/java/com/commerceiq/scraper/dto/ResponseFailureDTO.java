package com.commerceiq.scraper.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseFailureDTO {
    private String statusCode;
    private String message;
}
