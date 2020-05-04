package com.youtopin.zarinpal.domain;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder(builderMethodName = "hiddenBuilder", access = AccessLevel.PRIVATE)
@ToString
@NoArgsConstructor
public class PaymentRequest {
    @SerializedName("Amount")
    private Integer amount;
    @SerializedName("Description")
    private String description;
    @SerializedName("Email")
    private String email;
    @SerializedName("Mobile")
    private String mobile;
    @SerializedName("CallbackUrl")
    private String callbackUrl;

    public static PaymentRequestBuilder builder(Integer amount, String description){
        return hiddenBuilder().amount(amount).description(description);
    }
}
