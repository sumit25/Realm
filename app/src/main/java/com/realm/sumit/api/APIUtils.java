package com.realm.sumit.api;

import android.text.TextUtils;

import com.realm.sumit.R;
import com.realm.sumit.config.RealmApp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sumit on 26/07/17.
 */

public class APIUtils {

    private static Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
               //TODO String token = "";
                String token = RealmApp.getPreferences().getTokenDTO().getAccessToken();
                if (!TextUtils.isEmpty(token)) {
                    Request.Builder requestBuilder = request.newBuilder();
                    requestBuilder.addHeader("Authorization", "Bearer "+token);
                    String contentType = request.header("Content-Type");
                    if (null == contentType || contentType.contains("json")) {
                        requestBuilder.header("Content-Type", "application/json");
                    }
                    request = requestBuilder.build();
                }
                return chain.proceed(request);
            }
        };
    }

    private static OkHttpClient getOkHttpClient(Authenticator tokenAuthenticator, long readTimeout, long writeTimeout, long connectTimeout) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(getHeaderInterceptor())
                .authenticator(tokenAuthenticator)
                .readTimeout(readTimeout, TimeUnit.MINUTES)
                .writeTimeout(writeTimeout, TimeUnit.MINUTES)
                .connectTimeout(connectTimeout, TimeUnit.MINUTES)
                .build();
    }

    public static Retrofit initRetrofit(Authenticator tokenAuthenticator, long readTimeout, long writeTimeout, long connectTimeout) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(getOkHttpClient(tokenAuthenticator, readTimeout, writeTimeout, connectTimeout))
                .addConverterFactory(GsonConverterFactory.create());
        try {
            return retrofitBuilder.baseUrl("https://api.es-q.co/").build();
        } catch (Exception e) {
        }
        return retrofitBuilder.baseUrl("https://api.es-q.co/").build();
    }

    public static int getResponseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
