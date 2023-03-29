package com.heyd.blogapi.adapter.persistence.service;

import com.heyd.blogapi.adapter.persistence.repository.PopularKeywordRepository;
import com.heyd.blogapi.entity.PopularKeywordJpaEntity;
import com.heyd.blogapi.port.out.IncrementKeywordCountRdbOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class PopularKeywordCountAdapter implements IncrementKeywordCountRdbOutputPort {

    private final PopularKeywordRepository repository;

    public PopularKeywordCountAdapter(PopularKeywordRepository repository) {
        this.repository = repository;
    }

    @Override
    public long insertOrUpdateKeyword(String keyword) {
        var popularKeyword = repository.findByKeyword(keyword)
                .orElseGet(() -> new PopularKeywordJpaEntity(keyword));

        popularKeyword.incrementCount();

        if (isNotExist(popularKeyword)) {
            repository.save(popularKeyword);
        }

        return popularKeyword.getCount();
    }

    private static boolean isNotExist(PopularKeywordJpaEntity popularKeywordJpaEntity) {
        return Objects.isNull(popularKeywordJpaEntity.getId());
    }
}
