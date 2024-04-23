package com.zercok.demotest.service;

import com.zercok.demotest.domain.TodoVO;
import com.zercok.demotest.dto.PageRequestDTO;
import com.zercok.demotest.dto.PageResponseDTO;
import com.zercok.demotest.dto.TodoDTO;

import java.awt.print.Pageable;
import java.util.List;

public interface TodoService{
    //Todo 등록 서비스
    void register(TodoDTO todoDTO);

    // Todo 목록 서비스
//    List<TodoDTO> getAll();

    // Paging 목록 서비스
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    // Todo 조회 서비스
    TodoDTO getOne(Long tno);

    // Todo 삭제 서비스
    void remove(Long tno);

    // Todo 수정 서비스
    void modify(TodoDTO todoDTO);
}
