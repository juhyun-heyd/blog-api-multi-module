package com.heyd.blogapi.adapter.persistence.config;

import com.heyd.blogapi.adapter.persistence.config.properties.KakaoSearchProperties;
import com.heyd.blogapi.adapter.persistence.config.properties.NaverSearchProperties;
import com.heyd.blogapi.adapter.persistence.config.properties.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
        KakaoSearchProperties.class,
        NaverSearchProperties.class,
        RedisProperties.class
})
public class PropertiesConfig {
}
