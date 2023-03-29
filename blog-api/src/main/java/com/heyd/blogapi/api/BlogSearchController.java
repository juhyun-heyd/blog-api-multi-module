package com.heyd.blogapi.api;

import com.heyd.blogapi.api.dto.ApiResult;
import com.heyd.blogapi.api.dto.response.KakaoBlogSearchResponse;
import com.heyd.blogapi.api.dto.response.PopularSearchResponse;
import com.heyd.blogapi.api.mapper.PopularSearchMapper;
import com.heyd.blogapi.entity.KakaoResponseEntity;
import com.heyd.blogapi.port.in.BlogSearchKeywordCountCommandUseCase;
import com.heyd.blogapi.port.in.BlogSearchQueryUseCase;
import com.heyd.blogapi.port.in.PopularSearchKeywordQueryUseCase;
import constant.Constants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class BlogSearchController {
    private final BlogSearchQueryUseCase<?> blogSearchQueryUseCase;
    private final BlogSearchKeywordCountCommandUseCase blogSearchKeywordCountCommandUseCase;
    private final PopularSearchKeywordQueryUseCase popularSearchKeywordQueryUseCase;

    public BlogSearchController(
            BlogSearchQueryUseCase<?> blogSearchQueryUseCase,
            BlogSearchKeywordCountCommandUseCase blogSearchKeywordCountCommandUseCase,
            PopularSearchKeywordQueryUseCase popularSearchKeywordQueryUseCase
    ) {
        this.blogSearchQueryUseCase = blogSearchQueryUseCase;
        this.blogSearchKeywordCountCommandUseCase = blogSearchKeywordCountCommandUseCase;
        this.popularSearchKeywordQueryUseCase = popularSearchKeywordQueryUseCase;
    }

    @GetMapping("/search/blog/{keyword}")
    public ApiResult<KakaoBlogSearchResponse> searchBlog(
            @PathVariable String keyword,
            @RequestParam(required = false, defaultValue = "accurancy") String sort,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) throws InterruptedException {
        // TODO: Queue 도입
        blogSearchKeywordCountCommandUseCase.command(keyword);

        final var pageRequest = PageRequest.of(page, size, Sort.by(sort).descending());
        final KakaoResponseEntity responseEntity = (KakaoResponseEntity) blogSearchQueryUseCase.query(keyword, pageRequest);

        return ApiResult.ok(
                KakaoBlogSearchResponse.toResponseDto(responseEntity)
        );
    }

    @GetMapping("/search/popular-keyword")
    public ApiResult<List<PopularSearchResponse>> searchPopularKeyword() {
        final var entity = popularSearchKeywordQueryUseCase.query(
                Constants.POPULAR_KEYWORD_LIST_START,
                Constants.POPULAR_KEYWORD_LIST_END
        );

        return ApiResult.ok(
                PopularSearchMapper.toResponse(entity)
        );
    }

}
