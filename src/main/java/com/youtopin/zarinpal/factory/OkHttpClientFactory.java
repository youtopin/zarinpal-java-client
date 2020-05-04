package com.youtopin.zarinpal.factory;

import com.youtopin.zarinpal.UserAgentInterceptor;
import okhttp3.OkHttpClient;

public class OkHttpClientFactory {
    private static volatile OkHttpClient okHttpClient;

    private OkHttpClientFactory(){
        if(okHttpClient != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static OkHttpClient okHttpClient(){
        if(okHttpClient == null){
            okHttpClient = new OkHttpClient();
            okHttpClient.networkInterceptors().add(new UserAgentInterceptor("ZarinPal Rest Api v1"));
        }
        return okHttpClient;
    }

}
