package com.youtopin.zarinpal.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    @SerializedName("Status")
    private Integer status;
    @SerializedName("Authority")
    private String authority;
    private transient String url;
}
