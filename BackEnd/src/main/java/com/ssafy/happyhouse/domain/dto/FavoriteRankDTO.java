package com.ssafy.happyhouse.domain.dto;

import com.ssafy.happyhouse.domain.entity.HouseInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoriteRankDTO {
    private HouseInfo houseInfo;
    private int cnt;
}
