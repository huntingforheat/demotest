package com.zercok.demotest.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor
public class SampleService {

    //@Autowired  // 1.
    @Qualifier("normal")
    private final SampleDAO sampleDAO;    // 2. final로 지정하고, RequiredArgsConstructor을 지정하면 생성자 주입이 일어남

//    @Autowired 3번쨰 방법 setter 주입
//    public void setSampleDAO(SampleDAO sampleDAO01) {
//        this.sampleDAO = sampleDAO01
//    }
}
