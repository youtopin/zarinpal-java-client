package com.youtopin.zarinpal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    @JsonProperty("Status")
    private Integer status;
    @JsonProperty("Authority")
    private String authority;
    private transient String url;
}
