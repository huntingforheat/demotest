package com.zercok.demotest.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정할떄 사용하는 어노테이션 spring bean에 대한 설정하는 클래스 명시
public class ModelMapperConfig {

    @Bean   // servelt-context.xml에서 bean 설정을 자바 코드로 한 것
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)  // 접근 레벨 설정 private
                .setMatchingStrategy(MatchingStrategies.STRICT); // strict는 타입과 이름이 같은 애들을 매칭 하겠다.

        return modelMapper;
    }

}
