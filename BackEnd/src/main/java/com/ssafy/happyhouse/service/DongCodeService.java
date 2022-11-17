package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.DongDTO;

import java.util.List;

public interface DongCodeService {
    List<String> findGugun(String sido);

    List<String> findDong(String sido, String gugun);

    List<DongDTO> findDongCode(String sido, String gugun, String dong);
}
