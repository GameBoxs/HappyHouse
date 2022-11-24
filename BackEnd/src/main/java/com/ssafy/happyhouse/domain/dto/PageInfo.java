package com.ssafy.happyhouse.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageInfo {
    private int Page;
    private final int SIZE = 10;

    public PageInfo(int page) {
        Page = page;
    }
}
