package com.zercok.demotest.controller;

import com.zercok.demotest.dto.PageRequestDTO;
import com.zercok.demotest.dto.TodoDTO;
import com.zercok.demotest.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    public void list(@Valid PageRequestDTO pageRequestDTO,
                     BindingResult bindingResult,
                     Model model) {
        log.info(pageRequestDTO);
        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build(); // pageRequestDTO의 lombok(지정해 주지 않으면) Builder.default값이 들어감
        }
        log.info(pageRequestDTO);
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
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

    // 한개의 Todo 조회하기
    @GetMapping({"/read", "/modify"})   // /read로 들어오면 read.jsp로, /modify로 들어오면 modify.jsp로 이동
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model) {
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto", todoDTO);
    }

    // Todo 삭제하기
    @PostMapping("/remove")
    public String remove(Long tno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("-----------remove-------");
        log.info("tno : "+ tno);

        todoService.remove(tno);

        // 삭제시에는 페이지 번호를 1로, 사이즈는 전달
//        redirectAttributes.addAttribute("page", 1);
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/todo/list?"+pageRequestDTO.getLink();
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO,
                       PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("has errors.....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());   // GET 파라미터
//            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
//            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/todo/modify?"+pageRequestDTO.getLink(); // /todo/modify?tno=1(todoDTO.getTno()의 값)
        }
        log.info("modify........");
        log.info(todoDTO);

        todoService.modify(todoDTO);

        // 수정한 게시물이 있는 페이지 번호로 받아옴, 사이즈 그대로 전달
//        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/todo/read?"+pageRequestDTO.getLink();
    }


}
