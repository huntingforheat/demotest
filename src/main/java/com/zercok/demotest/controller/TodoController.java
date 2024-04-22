package com.zercok.demotest.controller;

import com.zercok.demotest.dto.TodoDTO;
import com.zercok.demotest.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/list") // /todo/list 접근시 해당 경로로 접근
    public void list() {
        log.info("todo.list");
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("todo.register");
    }

    // 4. 객체 자료 수집
    @PostMapping(value = "/register") // jsp method가 post일 경우 여기로 들어옴
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult, // DTO 검증
                               RedirectAttributes redirectAttributes) {
        log.info("Post todo register");

        if(bindingResult.hasErrors()) { // bindingResult가 에러나면 ex) title에 값을 안넣으면
            log.info("has errors.....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);  // todoservice에서 tododto에 등록하기

        return "redirect:/todo/list";   // 등록 후 목록으로 이동
    }
}
