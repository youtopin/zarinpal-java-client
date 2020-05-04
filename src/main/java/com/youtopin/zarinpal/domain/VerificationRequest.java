package com.youtopin.zarinpal.domain;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerificationRequest {
    @SerializedName("Authority")
    private String authority;
    @SerializedName("Amount")
    private Integer amount;
}
