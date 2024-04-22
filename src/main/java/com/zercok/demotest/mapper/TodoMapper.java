package com.zercok.demotest.mapper;

import com.zercok.demotest.domain.TodoVO;

public interface TodoMapper {
    // Todo mybatis 작업을 위한 인터페이스

    String getTime();

    void insert(TodoVO todoVO);
}
