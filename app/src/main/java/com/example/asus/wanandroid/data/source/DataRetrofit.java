package com.example.asus.wanandroid.data.source;


import com.jy.fistga.AppConstact;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * created by taofu on 2018/8/29
 **/
public class DataRetrofit {


    private static final long DEFAULT_TIMEOUT = 20000;

    private static FirstgaService INSTANCE;


    private DataRetrofit(){}



    public static FirstgaService getRetrofitService() {

        if(INSTANCE == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    /* .addInterceptor(new Interceptor() {
                         @Override
                         public Response intercept(Chain chain) throws IOException {
                             Request request = chain.request();
                             Request.Builder requestBuilder = request.newBuilder();
                             request = requestBuilder
                                    // .addHeader("Content-Type", " ation/x-www-form-urlencoded")
                                     //.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                                             //
                                     // bodyToString(request.body())))//关键部分，设置requestBody的编码格式为json
                                     .build();
                             return chain.proceed(request);
                         }
                     })*/
                    .addInterceptor(logging)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(AppConstact.BASE_URL)
                    .build();

            INSTANCE = retrofit.create(FirstgaService.class);
        }

        return INSTANCE;
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}