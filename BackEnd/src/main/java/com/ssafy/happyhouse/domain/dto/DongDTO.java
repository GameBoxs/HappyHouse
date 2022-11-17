package com.ssafy.happyhouse.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DongDTO {
    private String dongCode;
    private String name;
}
