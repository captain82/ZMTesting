package com.captain.ak.zmtesting;

import android.content.Context;

import com.captain.ak.zmtesting.interfaces.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZomatoApi {

    private final Retrofit retrofit;

    private ZomatoApi(Retrofit retrofit)
    {
        this.retrofit = retrofit;
    }

    public static class Builder
    {
        SessionConfig session;

        Builder(SessionConfig session)
        {
            this.session = session;
        }


        public ZomatoApi build(Context context)
        {
            if (InterceptorHttpClientCreator.getOkHttpClient()==null)
            {
                return null;
            }else
            {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://developers.zomato.com")
                        .client(InterceptorHttpClientCreator.getOkHttpClient())
                        .build();
                return new ZomatoApi(retrofit);
            }
        }

    }

    public static Builder with(SessionConfig session)
    {
        return new Builder(session);
    }

    public Service createService()
    {
        return retrofit.create(Service.class);
    }


}
