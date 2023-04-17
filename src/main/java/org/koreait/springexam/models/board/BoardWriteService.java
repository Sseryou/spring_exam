package org.koreait.springexam.models.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardWriteService {

    @Autowired
    private BoardFormValidator validator;

    private BoardDao boardDao;
    @Autowired
    public void setBoardDao(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    public void write(BoardForm boardForm){
        Board board = new Board();
        board.setSubject(boardForm.getSubject());
        board.setContent(boardForm.getContent());

        validator.Check(boardForm);

        boardDao.insert(board);
    }




}
