package com.wedevol.emptyspringrest.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = "wenxin")
public class WxyiyanConfigProperties implements Serializable {

    private static String apiKey;

    @Value(value = "${wenxin.url}")
    private static String url;

    @Value(value = "${wenxin.model}")
    private static String model;


}
