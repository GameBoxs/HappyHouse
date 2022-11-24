package com.ssafy.happyhouse.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDTO {
    @NotBlank
    private Long boardId;
    @NotBlank
    private String content;
}
