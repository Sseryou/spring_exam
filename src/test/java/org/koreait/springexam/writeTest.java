package org.koreait.springexam;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.springexam.board.Board;
import org.koreait.springexam.board.BoardDao;
import org.koreait.springexam.errors.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class writeTest {

    Board board = new Board();
    @Autowired
    private BoardDao boardDao;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("제목 또는 내용을 입력하지 않으면 에러 메세지 출력 확인")
    public void writeOutputError() throws Exception{
            String errorcheck = mockMvc.perform(post("/board/write"))
                    .andReturn().getResponse().getContentAsString();

            assertTrue(errorcheck.contains("제목을 입력하세요."));
            assertTrue(errorcheck.contains("내용을 입력하세요."));

    }


    @Test
    @DisplayName("게시글 작성 성공시 예외발생 하지 않음")
    public void writeSuccess(){

        board.setSubject("제목1");
        board.setContent("내용1");

        boolean result = boardDao.insert(board);

        assertTrue(result);

    }

    @Test
    @DisplayName("게시글 작성 중 누락 된 곳이 있으면 예외 발생")
    public void writeValid(){


        assertThrows(RuntimeException.class, () ->{
            board.setSubject("제목2");
            board.setContent("");

            boolean result = boardDao.insert(board);
        });
    }






}
