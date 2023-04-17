package org.koreait.springexam.errors;

import org.koreait.springexam.board.BoardController;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ExceptionHandler(RuntimeException.class)
public class BadRequestException{

}
