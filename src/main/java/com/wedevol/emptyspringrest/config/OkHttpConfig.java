package com.wedevol.emptyspringrest.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(300,TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS).build();
    }
}