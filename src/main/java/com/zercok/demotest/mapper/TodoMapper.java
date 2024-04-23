package com.zercok.demotest.mapper;

import com.sun.tools.javac.comp.Todo;
import com.zercok.demotest.domain.TodoVO;

import java.util.List;

public interface TodoMapper {
    // Todo mybatis 작업을 위한 인터페이스

    String getTime();

    //Todo 등록
    void insert(TodoVO todoVO);

    // Todo 목록
    List<TodoVO> selectAll();

    // Todo 조회 기능
    TodoVO selectOne(Long tno);

    // Todo 삭제 기능
    void delete(Long tno);

    // Todo 수정 기능
    void update(TodoVO todoVO);
}
