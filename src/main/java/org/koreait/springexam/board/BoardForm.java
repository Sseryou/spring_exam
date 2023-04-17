package org.koreait.springexam.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoardForm {
    @NotBlank(message = "제목을 입력하세요.")
    private String subject;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;
}
