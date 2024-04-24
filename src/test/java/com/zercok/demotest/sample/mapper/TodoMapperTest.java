package com.zercok.demotest.sample.mapper;

import com.sun.tools.javac.comp.Todo;
import com.zercok.demotest.domain.TodoVO;
import com.zercok.demotest.dto.PageRequestDTO;
import com.zercok.demotest.mapper.TodoMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
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
public class TodoMapperTest {

    @Autowired(required = false)    // 직접참조가 없어도 불러올수 있게 하면 false
    private TodoMapper todoMapper;

    @Test
    public void testTimeNow() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void insertTest() {

        TodoVO todoVO = TodoVO.builder()
                .title("test-mybatis")
                .dueDate(LocalDate.of(2024, 04, 22))
                .writer("user00")
                .build();
        todoMapper.insert(todoVO); // 값 들어가면 1 출력
    }

    @Test
    public void selectAllTest() {

        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void selectOneTest() {
        TodoVO todoVO = todoMapper.selectOne(3L);
        log.info(todoVO);
    }

    @Test
    public void deleteTest() {
        todoMapper.delete(1L);
        TodoVO todoVO = todoMapper.selectOne(1L);
        Assertions.assertNull(todoVO);
    }

    @Test
    public void updateTest() {
        TodoVO todoVO = todoMapper.selectOne(2L);
        TodoVO todoVO1 = TodoVO.builder()
                .tno(todoVO.getTno())
                .title(todoVO.getTitle() + "modify")
                .dueDate(todoVO.getDueDate())
                .finished(!todoVO.isFinished())
                .build();
        todoMapper.update(todoVO1);
        log.info(todoMapper.selectOne(todoVO.getTno()));
    }

    @Test
    public void skipTest() {
        // 1. PageRequestDTo 생성
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(3)
                .size(10)
                .build();
        // 2. todoMapper.selectList()를 테이스트 결과를 List<TodoVO>로 담기
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        // 3. 내용 출력
        voList.forEach(vo -> log.info(vo));
    }

//    @Test
//    public void countTest() {
//        int count = todoMapper.getCount(new PageRequestDTO(1, 10));
//        log.info("전체 게시글 수 : " + count);
//    }

    @Test
    public void testSelectSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t", "w"})
                .finished(true)
                .from(LocalDate.of(2024, 04, 20))
                .to(LocalDate.of(2024, 04, 30))
                .keyword("siu")
                .build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void getCountTest() {
        
    }
}
