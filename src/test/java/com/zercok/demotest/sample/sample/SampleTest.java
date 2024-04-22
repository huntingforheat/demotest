package com.zercok.demotest.sample.sample;

import com.zercok.demotest.sample.SampleService;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2 // 로그 처리
@ExtendWith(SpringExtension.class)  // Spring 동작
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")  // 설정 정보 위치
public class SampleTest {

    @Autowired  // 의존성 주입 (선언해줘야 의존성 주입이 일어남)
    private SampleService sampleService; // ioc SampleService에 주입하겠다는 뜻

    @Autowired
    private DataSource dataSource;  // hikari 데이터 소스가 들어옴

    @Autowired // sqlsessionfactory 테스트
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testService1() {
        log.info(sampleService);
        Assertions.assertNotNull(sampleService);
    }

    @Test // jdbc 연동 테스트
    public void testConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        log.info(connection);
        Assertions.assertNotNull(connection);   // Assertions은 테스트 결과를 확인해주는것

        connection.close();
    }

    @Test // SqlSessionFactory 연동 테스트
    public void testSqlSessionFactory() throws Exception {
        log.info(sqlSessionFactory);
        Assertions.assertNotNull(sqlSessionFactory);   // Assertions은 테스트 결과를 확인해주는것
    }
}
