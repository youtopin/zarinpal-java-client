package com.youtopin.zarinpal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationResponse {
    @JsonProperty("RefID")
    private Long refId;
    @JsonProperty("Status")
    private Integer status;
}
