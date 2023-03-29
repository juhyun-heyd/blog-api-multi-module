package com.heyd.blogapi.adapter.persistence.repository;

import com.heyd.blogapi.entity.PopularKeywordJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PopularKeywordRepository extends JpaRepository<PopularKeywordJpaEntity, Long> {

    Optional<PopularKeywordJpaEntity> findByKeyword(String keyword);

}
