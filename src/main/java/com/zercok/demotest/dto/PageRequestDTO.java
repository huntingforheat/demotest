package com.zercok.demotest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    // 페이지 처리를 위해서 페이지번호와 한 페이지당 보여줄 데이터의 수가 필요함.
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)    // 밑에 3개 @Valid에 종속
    @Max(value = 100)
    @Positive
    private int size = 10;

    public int getSkip() {  // 다음 버튼 눌렀을때
        return (page - 1) * size;
    }
}
