package com.ssafy.happyhouse.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoriteCountDTO {

    private Long aptCode;
    private Long cnt;
}
