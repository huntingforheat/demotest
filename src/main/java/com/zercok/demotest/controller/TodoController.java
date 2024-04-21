package com.zercok.demotest.controller;

import com.zercok.demotest.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {

    @RequestMapping("/list") // /todo/list 접근시 해당 경로로 접근
    public void list() {
        log.info("todo.list");
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("todo.register");
    }

    // 4. 객체 자료 수집
    @RequestMapping(value = "/register") // jsp method가 post일 경우 여기로 들어옴
    public void registerPost(TodoDTO todoDTO) {
        log.info("Post todo register");
        log.info(todoDTO);
    }
}
