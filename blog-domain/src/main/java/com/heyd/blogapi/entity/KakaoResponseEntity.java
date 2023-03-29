package com.heyd.blogapi.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.ZonedDateTime;
import java.util.List;

public class KakaoResponseEntity {

    private Meta meta;

    private List<Document> documents;

    public Meta getMeta() {
        return meta;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Meta {
        private Integer totalCount;
        private Integer pageableCount;
        private Boolean isEnd;

        public Meta(Integer totalCount, Integer pageableCount, Boolean isEnd) {
            this.totalCount = totalCount;
            this.pageableCount = pageableCount;
            this.isEnd = isEnd;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public Integer getPageableCount() {
            return pageableCount;
        }

        public Boolean getEnd() {
            return isEnd;
        }
    }

    public static class Document {
        private String title;
        private String contents;
        private String url;
        private String blogName;
        private String thumbnail;
        private ZonedDateTime datetime;

        public Document(String title, String contents, String url, String blogName, String thumbnail, ZonedDateTime datetime) {
            this.title = title;
            this.contents = contents;
            this.url = url;
            this.blogName = blogName;
            this.thumbnail = thumbnail;
            this.datetime = datetime;
        }

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }

        public String getUrl() {
            return url;
        }

        public String getBlogName() {
            return blogName;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public ZonedDateTime getDatetime() {
            return datetime;
        }
    }

    public KakaoResponseEntity(Meta meta, List<Document> documents) {
        this.meta = meta;
        this.documents = documents;
    }
}
