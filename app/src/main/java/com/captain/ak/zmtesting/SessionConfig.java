package com.captain.ak.zmtesting;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;

public class SessionConfig {

    public String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public static class Builder
    {
        private String apiKey;

        public Builder setApiKey(String apiKey)
        {
            this.apiKey = apiKey;
            return this;
        }

        @Deprecated
        public SessionConfig build()
        {
            return new SessionConfig(apiKey);
        }

        public SessionConfig build(Context context)
        {
            InterceptorHttpClientCreator.createInterceptorHttpClientCreator(context,apiKey);
            return new SessionConfig(apiKey);
        }
    }

    private SessionConfig(String apiKey)
    {
        this.apiKey = apiKey;
        Log.i("apikey" , apiKey);
    }
}
