package com.heyd.blogapi.api.dto.request;

public class KakaoBlogSearchRequest {

    private String query;
    private String sort;
    private int page;
    private int size;

    public KakaoBlogSearchRequest(String query, String sort, int page, int size) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    public String getQuery() {
        return query;
    }

    public String getSort() {
        return sort;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
