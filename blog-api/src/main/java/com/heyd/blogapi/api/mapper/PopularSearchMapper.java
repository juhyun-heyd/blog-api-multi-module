package com.heyd.blogapi.api.mapper;

import com.heyd.blogapi.api.dto.response.PopularSearchResponse;
import com.heyd.blogapi.entity.PopularSearchEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PopularSearchMapper {

    public static PopularSearchResponse toResponse(PopularSearchEntity entity) {
        return new PopularSearchResponse(
                entity.getKeyword(),
                entity.getCount()
        );
    }

    public static List<PopularSearchResponse> toResponse(List<PopularSearchEntity> entities) {
        return entities.stream()
                .map(PopularSearchMapper::toResponse)
                .collect(Collectors.toList());
    }

}
