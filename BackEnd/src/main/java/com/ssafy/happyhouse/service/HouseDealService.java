package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.entity.HouseDeal;
import com.ssafy.happyhouse.repository.HouseDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseDealService {

    private final HouseDealRepository houseDealRepository;

    public List<HouseDeal> findDealByDongCode(String dongCode) {
        return houseDealRepository.findAllDeal(dongCode);
    }

    public List<HouseDeal> findDealByAptCode(Long aptCode) {
        return houseDealRepository.findAllByHouseInfo_AptCode(aptCode)
                .stream()
                .sorted((deal1, deal2) -> -Integer.compare(getDataInt(deal1), getDataInt(deal2)))
                .collect(Collectors.toList());
    }

    private int getDataInt(HouseDeal deal) {
        return deal.getDealYear() * 10000 + deal.getDealMonth() * 100 + deal.getDealDay();
    }
}
