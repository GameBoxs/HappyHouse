package com.ssafy.happyhouse.domain.dto;

import com.ssafy.happyhouse.domain.entity.HouseInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FavoriteRankDTO {
    private Long aptCode;
    private String houseName;
    private Long cnt;
}
