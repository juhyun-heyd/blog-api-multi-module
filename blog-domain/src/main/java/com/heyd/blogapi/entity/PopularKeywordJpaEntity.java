package com.heyd.blogapi.entity;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PopularKeywordJpaEntity extends BaseTimeEntity {

    protected PopularKeywordJpaEntity() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String keyword;

    @Column
    private Long count;

    public PopularKeywordJpaEntity(String keyword) {
        this(keyword, 0L);
    }

    public PopularKeywordJpaEntity(String keyword, Long count) {
        Assert.state(count >= 0, "count must greater than zero");

        this.keyword = keyword;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public Long getCount() {
        return count;
    }

    public void incrementCount() {
        this.count += 1;
    }
}
