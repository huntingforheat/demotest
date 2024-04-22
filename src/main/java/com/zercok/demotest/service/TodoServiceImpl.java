package com.zercok.demotest.service;

import com.zercok.demotest.domain.TodoVO;
import com.zercok.demotest.dto.TodoDTO;
import com.zercok.demotest.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor // 생성자 주입
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;

    private final ModelMapper modelMapper;  // DTO를 VO로 VO를 DTO로

    @Override
    public void register(TodoDTO todoDTO) {
        log.info(modelMapper);

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class); // dto -> vo 로 변경?  mybatis가 dao을 대체?

        log.info(todoVO);

        todoMapper.insert(todoVO);
    }
}
