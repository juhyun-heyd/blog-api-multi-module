package com.heyd.blogapi.entity;

public class PopularSearchEntity {

    private String keyword;
    private Long count;

    public String getKeyword() {
        return keyword;
    }

    public Long getCount() {
        return count;
    }

    public PopularSearchEntity(String keyword, Long count) {
        this.keyword = keyword;
        this.count = count;
    }

}
