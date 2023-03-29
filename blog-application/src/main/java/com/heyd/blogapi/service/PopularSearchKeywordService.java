package com.heyd.blogapi.service;

import com.heyd.blogapi.entity.PopularSearchEntity;
import com.heyd.blogapi.port.in.PopularSearchKeywordQueryUseCase;
import com.heyd.blogapi.port.out.PopularSearchKeywordOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PopularSearchKeywordService implements PopularSearchKeywordQueryUseCase {

    private final PopularSearchKeywordOutputPort outputPort;

    public PopularSearchKeywordService(PopularSearchKeywordOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    private static final String KEY_PREFIX = "com.heyd.blog_search_keyword:";

    // TODO: api 모듈과 의존성 제약 설정
    @Override
    public List<PopularSearchEntity> query(long start, long end) {
        final var topNKeyword = outputPort.searchPopularKeywordList(KEY_PREFIX, start, end);

        return topNKeyword.stream()
                .map(tuple -> new PopularSearchEntity(tuple.getValue(), tuple.getScore().longValue()))
                .collect(Collectors.toList());
    }
}
