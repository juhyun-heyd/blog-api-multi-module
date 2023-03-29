package com.heyd.blogapi.adapter.persistence.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@ConstructorBinding
@ConfigurationProperties("blog.search.kakao")
public class KakaoSearchProperties {

    @NotBlank
    private final String host;

    @NotBlank
    private final String uri;

    @NotBlank
    private final String key;

    public KakaoSearchProperties(String host, String uri, String key) {
        this.host = host;
        this.uri = uri;
        this.key = key;
    }

    public String getHost() {
        return host;
    }

    public String getUri() {
        return uri;
    }

    public String getKey() {
        return key;
    }
}
