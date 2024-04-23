package com.zercok.demotest.service;

import com.zercok.demotest.domain.TodoVO;
import com.zercok.demotest.dto.PageRequestDTO;
import com.zercok.demotest.dto.PageResponseDTO;
import com.zercok.demotest.dto.TodoDTO;
import com.zercok.demotest.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

//    @Override
//    public List<TodoDTO> getAll() {
//        List<TodoDTO> dtoList = todoMapper.selectAll().stream()// 스트림으로 TodoVo를 가져옴
//                .map(vo -> modelMapper.map(vo, TodoDTO.class))
//                .collect(Collectors.toList());
//        return dtoList;
//    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        // PageRequestDTO를 이용한 게시물 가져오기
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        // 가져온 voList를 dtoList로 변환
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());
        // total 게시물
        int total = todoMapper.getCount(pageRequestDTO);
        // 반환할 PageResponseDTO를 생성
        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }

    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO todoVO = todoMapper.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.update(todoVO);
    }
}
