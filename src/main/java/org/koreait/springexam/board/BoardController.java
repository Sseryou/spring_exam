package org.koreait.springexam.board;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private BoardWriteService service;

    @GetMapping("/write")
    public String write(Model model){
        BoardForm boardForm = new BoardForm();
        model.addAttribute("boardForm", boardForm);

        return "board/write";
    }

    @PostMapping("/write")
    public String writePs(@Valid BoardForm boardForm, Errors errors){
        if(errors.hasErrors()){
            return "board/write";
        }
        service.write(boardForm);
        return "redirect:/board/list";
    }


}
