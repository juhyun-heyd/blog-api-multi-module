package com.heyd.blogapi.adapter.persistence.http;

import com.heyd.blogapi.adapter.persistence.config.properties.KakaoSearchProperties;
import com.heyd.blogapi.entity.KakaoRequestEntity;
import com.heyd.blogapi.entity.KakaoResponseEntity;
import com.heyd.blogapi.exception.WebClientBadRequestException;
import com.heyd.blogapi.exception.WebClientBadResponseException;
import com.heyd.blogapi.port.out.KakaoApiClientOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class KakaoApiClientAdapter implements
        BlogSearchApiClientAdapter<KakaoRequestEntity, KakaoResponseEntity>,
        KakaoApiClientOutputPort {

    private final Logger log = LoggerFactory.getLogger(KakaoApiClientAdapter.class);

    private final WebClient webClient;

    private final KakaoSearchProperties properties;

    public KakaoApiClientAdapter(WebClient webClient, KakaoSearchProperties properties) {
        this.webClient = webClient;
        this.properties = properties;
    }

    @Override
    public Mono<KakaoResponseEntity> searchBlog(KakaoRequestEntity request) {
        return webClient
                .method(HttpMethod.GET)
                .uri(builder -> builder.path(properties.getUri())
                        .queryParam("query", request.getQuery())
                        .queryParam("sort", request.getSize())
                        .queryParam("page", request.getPage())
                        .queryParam("size", request.getSize())
                        .build()
                )
                .retrieve()
                // TODO: 장애 발생 시 네이버 블로그 검색 API 호출하도록 우회
                .onStatus(
                        HttpStatus::is4xxClientError,
                        response -> response.bodyToMono(String.class).flatMap(
                                body -> {
                                    log.error("4xx Client Error - statusCode: {}, response: {}", response.rawStatusCode(), body);

                                    return Mono.error(
                                            new WebClientBadRequestException(
                                                    response.rawStatusCode(),
                                                    body
                                            )
                                    );
                                })
                )
                .onStatus(
                        HttpStatus::is5xxServerError,
                        response -> response.bodyToMono(String.class).flatMap(
                                body -> {
                                    log.error("5xx Server Error - statusCode: {}, response: {}", response.rawStatusCode(), body);

                                    return Mono.error(
                                            new WebClientBadResponseException(
                                                    response.rawStatusCode(),
                                                    body
                                            )
                                    );
                                })
                )
                .bodyToMono(KakaoResponseEntity.class)
                .onErrorResume(WebClientBadRequestException.class, e -> Mono.empty());
    }

}
