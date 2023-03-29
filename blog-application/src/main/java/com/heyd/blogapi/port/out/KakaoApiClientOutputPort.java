package com.heyd.blogapi.port.out;

import com.heyd.blogapi.entity.KakaoRequestEntity;
import com.heyd.blogapi.entity.KakaoResponseEntity;
import reactor.core.publisher.Mono;

public interface KakaoApiClientOutputPort {

    Mono<KakaoResponseEntity> searchBlog(KakaoRequestEntity request);

}
