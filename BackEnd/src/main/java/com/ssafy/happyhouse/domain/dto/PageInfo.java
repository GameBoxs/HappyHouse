package com.ssafy.happyhouse.domain.dto;

import lombok.Data;

@Data
public class PageInfo {
    private int Page;
    private final int SIZE = 10;

    public PageInfo(int page) {
        Page = page;
    }
}
