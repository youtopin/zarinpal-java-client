package com.youtopin.zarinpal.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtopin.zarinpal.api.Address;
import com.youtopin.zarinpal.domain.PaymentRequest;
import com.youtopin.zarinpal.domain.PaymentResponse;
import com.youtopin.zarinpal.domain.VerificationRequest;
import com.youtopin.zarinpal.domain.VerificationResponse;
import com.youtopin.zarinpal.factory.OkHttpClientFactory;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;

@Log4j2
public class ZarinpalService {
    private final OkHttpClient okHttpClient;
    private final MerchantProvider merchantProvider;
    private final Address address;
    private final ObjectMapper objectMapper;
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public ZarinpalService(final String merchantId, Address address){
        this(new MerchantProvider() {
            @Override
            public String getMerchantId() {
                return merchantId;
            }
        }, address);
    }

    public ZarinpalService(MerchantProvider merchantProvider, Address address) {
        this.address = address;
        this.okHttpClient = OkHttpClientFactory.okHttpClient();
        this.merchantProvider = merchantProvider;
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @SneakyThrows
    public PaymentResponse paymentRequest(PaymentRequest paymentRequest){
        String json = objectMapper.writeValueAsString(new PaymentRequestWrapper(merchantProvider.getMerchantId(), paymentRequest));
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(address.paymentRequestAddress())
                .post(requestBody)
                .build();
        log.debug("Sending json -> " + json + " to " + address.paymentRequestAddress());
        Response response = okHttpClient.newCall(request).execute();
        PaymentResponse paymentResponse = objectMapper.readValue(response.body().string(), PaymentResponse.class);

        if(paymentResponse.getStatus() == 100){
            paymentResponse.setUrl(address.startPayAddress() + paymentResponse.getAuthority());
        }

        return paymentResponse;
    }

    @SneakyThrows
    public VerificationResponse paymentVerification(VerificationRequest verificationRequest){
        String json = objectMapper.writeValueAsString(new VerificationRequestWrapper(merchantProvider.getMerchantId(), verificationRequest));
        RequestBody requestBody = RequestBody.create(JSON, json);
        log.debug("Sending json -> " + json + " to " + address.paymentVerificationAddress());
        Request request = new Request.Builder()
                .url(address.paymentVerificationAddress())
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return objectMapper.readValue(response.body().string(), VerificationResponse.class);
    }


    @Getter
    private static class VerificationRequestWrapper extends VerificationRequest {
        @JsonProperty("MerchantID")
        private final String merchantId;

        public VerificationRequestWrapper(String merchantId, VerificationRequest verificationRequest) {
            super(verificationRequest.getAuthority(), verificationRequest.getAmount());
            this.merchantId = merchantId;
        }
    }

    @Getter
    private static class PaymentRequestWrapper extends PaymentRequest {
        @JsonProperty("MerchantID")
        private final String merchantId;

        private PaymentRequestWrapper(String merchantId, PaymentRequest paymentRequest) {
            this.merchantId = merchantId;
            this.setAmount(paymentRequest.getAmount());
            this.setCallbackUrl(paymentRequest.getCallbackUrl());
            this.setDescription(paymentRequest.getDescription());
            this.setEmail(paymentRequest.getEmail());
            this.setMobile(paymentRequest.getMobile());
        }
    }

}
