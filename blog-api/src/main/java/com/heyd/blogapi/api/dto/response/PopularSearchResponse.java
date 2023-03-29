package com.heyd.blogapi.api.dto.response;

public class PopularSearchResponse {

    private String keyword;
    private Long count;

    public String getKeyword() {
        return keyword;
    }

    public Long getCount() {
        return count;
    }

    public PopularSearchResponse(String keyword, Long count) {
        this.keyword = keyword;
        this.count = count;
    }
}
