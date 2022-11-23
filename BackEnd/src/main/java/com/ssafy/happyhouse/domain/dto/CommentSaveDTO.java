package com.ssafy.happyhouse.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
public class CommentSaveDTO {
    @NotBlank
    private Long boardId;
    @NotBlank
    private String content;
}
