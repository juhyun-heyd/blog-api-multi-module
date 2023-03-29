package com.heyd.blogapi.port.out;

public interface IncrementKeywordCountRedisOutputPort {

    double incrementScore(String key, String value, double delta);

}
