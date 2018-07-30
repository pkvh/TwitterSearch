package com.shilpa.TwitterSearch.api;

import com.shilpa.TwitterSearch.BuildConfig;
import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceBuilder {

    public static <T> T buildService(final Map<String, String> headers,Class<T> service) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                //Customize request
                Request.Builder requestBuilder = original.newBuilder();
               /* if(headers != null) {
                    for (String key : headers.keySet()) {
                        requestBuilder.header(key, headers.get(key));
                    }
                }*/

                Request request = requestBuilder.method(original.method(), original.body()).build();
                return chain.proceed(request);
            }
        }).build();

        return  new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(service);

        }

}
