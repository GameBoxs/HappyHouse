package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.DongDTO;

import java.util.List;

public interface DongCodeService {
    List<DongDTO> findGugun(String dongCode);

    List<DongDTO> findDong(String dongCode);
}
