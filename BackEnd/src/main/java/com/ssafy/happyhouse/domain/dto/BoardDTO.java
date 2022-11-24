package com.ssafy.happyhouse.domain.dto;

import com.ssafy.happyhouse.domain.enumurate.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private BoardType boardType;

    @NotBlank
    private String username;

    @NotBlank
    private String useremail;

    @NotBlank
    private LocalDateTime createTime;

    @NotBlank
    private int hit;

}
