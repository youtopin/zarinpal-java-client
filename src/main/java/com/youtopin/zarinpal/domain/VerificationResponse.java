package com.youtopin.zarinpal.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationResponse {
    @SerializedName("RefID")
    private Integer refId;
    @SerializedName("Status")
    private Integer status;
}
