package com.ssafy.happyhouse.domain.dto;

import com.ssafy.happyhouse.domain.enumurate.BoardType;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
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
    private LocalDateTime createTime;

    @NotBlank
    private int hit;

}
