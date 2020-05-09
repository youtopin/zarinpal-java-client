package com.youtopin.zarinpal.service;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.youtopin.zarinpal.api.Address;
import com.youtopin.zarinpal.domain.PaymentRequest;
import com.youtopin.zarinpal.domain.PaymentResponse;
import com.youtopin.zarinpal.domain.VerificationRequest;
import com.youtopin.zarinpal.domain.VerificationResponse;
import com.youtopin.zarinpal.factory.OkHttpClientFactory;
import lombok.Getter;
import lombok.SneakyThrows;
import okhttp3.*;

public class ZarinpalService {
    private final OkHttpClient okHttpClient;
    private final MerchantProvider merchantProvider;
    private final Address address;
    private final Gson gson;
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public ZarinpalService(MerchantProvider merchantProvider, Address address) {
        this.address = address;
        this.okHttpClient = OkHttpClientFactory.okHttpClient();
        this.merchantProvider = merchantProvider;
        gson = new Gson();
    }

    @SneakyThrows
    public PaymentResponse paymentRequest(PaymentRequest paymentRequest){
        String json = gson.toJson(new PaymentRequestWrapper(merchantProvider.getMerchantId(), paymentRequest));
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(address.paymentRequestAddress())
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        PaymentResponse paymentResponse = gson.fromJson(response.body().string(), PaymentResponse.class);

        if(paymentResponse.getStatus() == 100){
            paymentResponse.setUrl(address.startPayAddress() + paymentResponse.getAuthority());
        }

        return paymentResponse;
    }

    @SneakyThrows
    public VerificationResponse paymentVerification(VerificationRequest verificationRequest){
        String json = gson.toJson(new VerificationRequestWrapper(merchantProvider.getMerchantId(), verificationRequest));
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(address.paymentVerificationAddress())
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return gson.fromJson(response.body().string(), VerificationResponse.class);
    }


    @Getter
    private static class VerificationRequestWrapper extends VerificationRequest {
        @SerializedName("MerchantID")
        private final String merchantId;

        public VerificationRequestWrapper(String merchantId, VerificationRequest verificationRequest) {
            super(verificationRequest.getAuthority(), verificationRequest.getAmount());
            this.merchantId = merchantId;
        }
    }

    @Getter
    private static class PaymentRequestWrapper extends PaymentRequest {
        @SerializedName("MerchantID")
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
