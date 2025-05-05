package com.wedevol.emptyspringrest.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = "wenxin")
public class WxyiyanConfigProperties implements Serializable {

    private String apiKey;

    private String url;

    private String model;

}
