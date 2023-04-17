package org.koreait.springexam.models.board;


import org.koreait.springexam.errors.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class BoardFormValidator implements Validator<BoardForm>{


    @Override
    public void Check(BoardForm boardForm) {
        if(boardForm.getSubject() == null || boardForm.getSubject().isBlank()){
            throw new BadRequestException("제목을 입력하세요.");
        }

        if(boardForm.getContent() == null || boardForm.getContent().isBlank()){
            throw new BadRequestException("내용을 입력하세요.");
        }

    }
}
