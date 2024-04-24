package com.zercok.demotest.mapper;

import com.sun.tools.javac.comp.Todo;
import com.zercok.demotest.domain.TodoVO;
import com.zercok.demotest.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    // Todo mybatis 작업을 위한 인터페이스

    String getTime();

    //Todo 등록
    void insert(TodoVO todoVO);

    // Todo 목록
    List<TodoVO> selectAll();

    // Todo 조회 기능
    TodoVO selectOne(Long tno); // tno 기준으로 조회하니까 TodoVO의 tno 값을 가져옴

    // Todo 삭제 기능
    void delete(Long tno);

    // Todo 수정 기능
    void update(TodoVO todoVO);

    // 페이징 처리를 통한 목록 보기
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    // 전체 게시물 갯수 알아오기
    int getCount(PageRequestDTO pageRequestDTO);
}
