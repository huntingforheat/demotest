package com.zercok.demotest.sample.service;

import com.zercok.demotest.controller.TodoController;
import com.zercok.demotest.dto.PageRequestDTO;
import com.zercok.demotest.dto.PageResponseDTO;
import com.zercok.demotest.dto.TodoDTO;
import com.zercok.demotest.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Test...")
                .dueDate(LocalDate.now())
                .writer("user1")
                .build();

        todoService.register(todoDTO);
    }

//    @Test
//    public void testGetAll() {
//        List<TodoDTO> dtoList = todoService.getAll();
//        dtoList.forEach(dto ->log.info(dto));
//    }

//    @Test
//    public TodoDTO testgetOne(Long tno) {
//        TodoDTO todoDTO = todoService.getOne(tno);
//        log.info(todoDTO);
//    }

    @Test
    public void testPaging() {
        // 1. PageRequestDTO를 생성
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        // 2. ResponseDTO 값을 받는 테스트를 진행     서비스 처리
        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);

        log.info(responseDTO);
        // 3. 결과 확인
        responseDTO.getDtoList().stream().forEach(todoDTO -> log.info(todoDTO));
    }
}
