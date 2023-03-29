package com.heyd.blogapi.port.in;

import com.heyd.blogapi.entity.PopularSearchEntity;

import java.util.List;

public interface PopularSearchKeywordQueryUseCase {

    List<PopularSearchEntity> query(long start, long end);

}
