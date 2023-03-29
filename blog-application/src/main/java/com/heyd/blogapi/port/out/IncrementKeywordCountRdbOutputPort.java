package com.heyd.blogapi.port.out;

public interface IncrementKeywordCountRdbOutputPort {

    long insertOrUpdateKeyword(String keyword);

}
