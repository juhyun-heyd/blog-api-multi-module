package com.heyd.blogapi.port.in;

import org.springframework.data.domain.Pageable;

public interface BlogSearchQueryUseCase<T> {

    T query(String keyword, Pageable pageable);

}
