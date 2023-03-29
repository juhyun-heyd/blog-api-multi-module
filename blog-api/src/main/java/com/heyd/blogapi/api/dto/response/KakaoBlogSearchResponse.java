package com.heyd.blogapi.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.heyd.blogapi.entity.KakaoResponseEntity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class KakaoBlogSearchResponse {

    private Meta meta;

    private List<Document> documents;

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
    }

    public KakaoBlogSearchResponse(Meta meta, List<Document> documents) {
        this.meta = meta;
        this.documents = documents;
    }

    public static KakaoBlogSearchResponse toResponseDto(KakaoResponseEntity entity) {
        final var entityMeta = entity.getMeta();
        final var entityDocument = entity.getDocuments();

        final var meta = new Meta(entityMeta.getTotalCount(), entityMeta.getPageableCount(), entityMeta.getEnd());
        final var documents = entityDocument.stream()
                .map(document ->
                        new Document(
                                document.getTitle(),
                                document.getContents(),
                                document.getUrl(),
                                document.getBlogName(),
                                document.getThumbnail(),
                                document.getDatetime())
                )
                .collect(Collectors.toList());

        return new KakaoBlogSearchResponse(meta, documents);
    }
}
