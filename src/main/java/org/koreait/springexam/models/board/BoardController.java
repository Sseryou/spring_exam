package org.koreait.springexam.models.board;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private BoardWriteService service;


    @GetMapping("/list") // /board/list 경로의 get 신호 받을시 실행
    public String boardList(Model model){
        List<Board> boards = new ArrayList<>();

        boards = boardDao.gets();
        model.addAttribute("boards", boards);

        // list 페이지로 보낸다.
        return "board/list";
    }

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
