package com.ssafy.happyhouse.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardUpdateDTO {
    private Long id;
    private String title;
    private String content;
}
