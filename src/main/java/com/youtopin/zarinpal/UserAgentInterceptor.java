package com.youtopin.zarinpal;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class UserAgentInterceptor implements Interceptor {
    private final String userAgent;

    public UserAgentInterceptor(String userAgent) {
        this.userAgent = userAgent;
    }

    @NotNull
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                .header("User-Agent", userAgent)
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}
