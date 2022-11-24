package com.ssafy.happyhouse.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateDTO {
    private Long id;
    private String title;
    private String content;
}
