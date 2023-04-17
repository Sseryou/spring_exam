package org.koreait.springexam.models.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {

    private Long id;
    private String subject;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime modDt;

}
