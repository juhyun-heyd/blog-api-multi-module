package com.heyd.blogapi.service;

import com.heyd.blogapi.entity.KakaoRequestEntity;
import com.heyd.blogapi.entity.KakaoResponseEntity;
import com.heyd.blogapi.port.in.BlogSearchQueryUseCase;
import com.heyd.blogapi.port.out.KakaoApiClientOutputPort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class KakaoBlogSearchService implements BlogSearchQueryUseCase<KakaoResponseEntity> {

    private final KakaoApiClientOutputPort outputPort;

    public KakaoBlogSearchService(KakaoApiClientOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public KakaoResponseEntity query(String keyword, Pageable pageable) {
        Mono<KakaoResponseEntity> response = outputPort.searchBlog(
                new KakaoRequestEntity(
                        keyword,
                        pageable.getSort().toString(),
                        pageable.getPageNumber(),
                        pageable.getPageSize()
                )
        );

        return response.block();
    }
}
