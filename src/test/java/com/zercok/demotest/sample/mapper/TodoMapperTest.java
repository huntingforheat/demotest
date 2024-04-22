package com.zercok.demotest.sample.mapper;

import com.zercok.demotest.domain.TodoVO;
import com.zercok.demotest.mapper.TodoMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

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
}
