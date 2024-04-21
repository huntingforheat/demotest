package com.zercok.demotest.sample.mapper;

import com.zercok.demotest.mapper.TimeMapper;
import com.zercok.demotest.mapper.TimeMapper2;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTests {

    @Autowired(required = false)    // 직접참조가 없어도 불러올수 있게 하면 false
    private TimeMapper timeMapper;

    @Autowired(required = false)
    private TimeMapper2 timeMapper2;

    @Test
    public void testGetTime() {
        log.info(timeMapper.getTime());
        // 10:53:20  INFO [com.zercok.demotest.sample.mapper.TimeMapperTests] 2024-04-19 10:53:20 정상작동 로그
    } // root-context.xml의 mybatis:scan -> demotest.mapper 프로젝트안에 있는 TimeMapper @Select gettime을 불러옴 -> log 출력

    @Test
    public void testGetNow() {
        log.info(timeMapper2.getNow());
    }
}
