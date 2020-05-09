package com.youtopin.zarinpal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder(builderMethodName = "hiddenBuilder")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @JsonProperty("Amount")
    private Integer amount;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Mobile")
    private String mobile;
    @JsonProperty("CallbackURL")
    private String callbackUrl;

    public static PaymentRequestBuilder builder(Integer amount, String description){
        return hiddenBuilder().amount(amount).description(description);
    }
}
