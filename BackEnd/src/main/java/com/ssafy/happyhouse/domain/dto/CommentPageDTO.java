package com.ssafy.happyhouse.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentPageDTO {
    private Long id;
    private String content;
    private String writeUserName;
    private boolean isDelete;
    private LocalDateTime createTime;
}
