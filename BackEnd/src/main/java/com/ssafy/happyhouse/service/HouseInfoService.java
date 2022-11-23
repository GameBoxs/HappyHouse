package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.entity.DongCode;
import com.ssafy.happyhouse.domain.entity.HouseInfo;
import com.ssafy.happyhouse.exception.NoHouseException;
import com.ssafy.happyhouse.repository.DongCodeRepository;
import com.ssafy.happyhouse.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseInfoService {
    private final HouseInfoRepository houseInfoRepository;
    private final DongCodeRepository dongCodeRepository;

    public List<HouseInfo> findByDongCode(String dongCode) {
        DongCode dongCodeOp = dongCodeRepository.findByDongcode(dongCode)
                .orElseThrow(() -> new IllegalArgumentException("없는 동코드 입니다."));
        return houseInfoRepository.findAllByDongCode(dongCodeOp);
    }

    public HouseInfo findByAptCode(Long aptCode) {
        return houseInfoRepository.findByAptCode(aptCode)
                .orElseThrow(() -> new NoHouseException("NO HOUSE"));
    }
}
