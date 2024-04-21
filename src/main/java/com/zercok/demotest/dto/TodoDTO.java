package com.zercok.demotest.dto;

import lombok.*;

import java.time.LocalDate;

@ToString
@Data   //  import하면 getter,setter가 자동으로 만들어짐
@Builder
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
@NoArgsConstructor  // 기본 생성자 생성
public class TodoDTO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;
}
