package com.heyd.blogapi.adapter.persistence.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@ConstructorBinding
@ConfigurationProperties("blog.search.naver")
public class NaverSearchProperties {

    @NotBlank
    private final String host;

    @NotBlank
    private final String uri;

    @NotBlank
    private final String clientId;

    @NotBlank
    private final String clientSecret;

    public NaverSearchProperties(String host, String uri, String clientId, String clientSecret) {
        this.host = host;
        this.uri = uri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getHost() {
        return host;
    }

    public String getUri() {
        return uri;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
