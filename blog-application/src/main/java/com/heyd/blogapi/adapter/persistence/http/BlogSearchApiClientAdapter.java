package com.heyd.blogapi.adapter.persistence.http;

import reactor.core.publisher.Mono;

public interface BlogSearchApiClientAdapter<T, R> {

    Mono<R> searchBlog(T request);

}
