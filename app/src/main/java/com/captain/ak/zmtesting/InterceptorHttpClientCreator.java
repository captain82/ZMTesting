package com.captain.ak.zmtesting;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class InterceptorHttpClientCreator {

    private static OkHttpClient defaultHttpClient;

    public static void createInterceptorHttpClientCreator(final Context context,final String apiKey) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("user-key" ,apiKey)
                                .addHeader("Accept" , "application/json")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();
    }

    static OkHttpClient getOkHttpClient()
    {
        if (defaultHttpClient!=null)
        {
            return defaultHttpClient;
        }
        return null;
    }
}
