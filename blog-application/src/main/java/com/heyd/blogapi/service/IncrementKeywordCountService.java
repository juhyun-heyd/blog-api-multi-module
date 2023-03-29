package com.heyd.blogapi.service;

import com.heyd.blogapi.exception.DistributedLockException;
import com.heyd.blogapi.port.in.BlogSearchKeywordCountCommandUseCase;
import com.heyd.blogapi.port.out.IncrementKeywordCountRdbOutputPort;
import com.heyd.blogapi.port.out.IncrementKeywordCountRedisOutputPort;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class IncrementKeywordCountService implements BlogSearchKeywordCountCommandUseCase {

    private final IncrementKeywordCountRedisOutputPort redisOutputPort;
    private final IncrementKeywordCountRdbOutputPort rdbOutputPort;
    private final RedissonClient redissonClient;

    public IncrementKeywordCountService(
            IncrementKeywordCountRedisOutputPort redisOutputPort,
            IncrementKeywordCountRdbOutputPort rdbOutputPort,
            RedissonClient redissonClient
    ) {
        this.redisOutputPort = redisOutputPort;
        this.rdbOutputPort = rdbOutputPort;
        this.redissonClient = redissonClient;
    }

    private static final String KEY_PREFIX = "com.heyd.blog_search_keyword:";
    private static final String LOCK_NAME = "increment:keyword:count";
    private static final long WAIT_TIME = 1500;
    private static final long LEASE_TIME = 2000;

    @Async
    @Override
    public void command(String keyword) {
        final var lock = redissonClient.getLock(LOCK_NAME);

        try {
            final var isLocked = lock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.MILLISECONDS);
            if (!isLocked) {
                // TODO: Logging
                return;
            }

            redisOutputPort.incrementScore(KEY_PREFIX, keyword, 1.0d);

            rdbOutputPort.insertOrUpdateKeyword(keyword);
        } catch (InterruptedException e) {
            // TODO: Logging

            throw new DistributedLockException(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    String.format("Redisson getLock error: %s", LOCK_NAME)
            );
        } finally {
            lock.unlock();
        }
    }
}
