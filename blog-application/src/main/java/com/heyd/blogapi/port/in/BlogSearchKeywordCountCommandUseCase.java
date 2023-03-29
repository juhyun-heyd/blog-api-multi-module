package com.heyd.blogapi.port.in;

public interface BlogSearchKeywordCountCommandUseCase {

    void command(String keyword) throws InterruptedException;

}
