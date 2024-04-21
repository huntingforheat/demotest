package com.zercok.demotest.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
// @Primary // 인터페이스 상속한 녀석이 여러개가 있을경우 우선순위를 두는법
@Qualifier("event")
public class EventSampleDAOImpl implements SampleDAO {

}
