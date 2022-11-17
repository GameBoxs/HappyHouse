package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.entity.HouseDeal;
import com.ssafy.happyhouse.repository.HouseDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseDealService {

    private final HouseDealRepository houseDealRepository;

    public List<HouseDeal> findDealByDongCode(String dongCode) {
        return houseDealRepository.findAllDeal(dongCode);
    }
}
