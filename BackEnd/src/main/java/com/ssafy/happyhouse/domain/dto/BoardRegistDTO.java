package com.ssafy.happyhouse.domain.dto;

import com.ssafy.happyhouse.domain.enumurate.BoardType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class BoardRegistDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private BoardType boardType;
}
