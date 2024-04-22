package com.zercok.demotest.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice // Component여서 bean 등록
@Log4j2
public class CommonExceptionAdvice {
    @ResponseBody   // 데이터를 전달 (리졸버를 거치지않고 들어감)
    @ExceptionHandler(NumberFormatException.class)  // 어노테이션을 통해서 예외 상황 지정
    public String exceptNumber(NumberFormatException numberFormatException) {
        log.error("-------------------------");
        log.error(numberFormatException.getMessage());

        return "NUMBER FORMAT EXCEPTION";
    }

    // 개발 중에 Debuggin용으로 사용
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception) {
        log.error("-------------------------");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>" +exception.getMessage()+ "</li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)    // .class는 NoHandlerFoundException class 자체를 넘겨준다는것
    @ResponseStatus(HttpStatus.NOT_FOUND) // httpstatus.로 에러 페이지를 지정해줌
    public String notFound() {  // custom404.jsp를 참조
        return "custom404";
    }
}
