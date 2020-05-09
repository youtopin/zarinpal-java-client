package com.youtopin.zarinpal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerificationRequest {
    @JsonProperty("Authority")
    private String authority;
    @JsonProperty("Amount")
    private Integer amount;
}
