package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.entity.HouseInfo;
import com.ssafy.happyhouse.service.HouseInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houseinfo")
@Slf4j
public class HouseInfoController {

    private final HouseInfoService houseInfoService;

    @GetMapping("/dongcode/{dongcode}")
    public List<HouseInfo> getHouses(@PathVariable String dongcode) {
        List<HouseInfo> result = houseInfoService.findByDongCode(dongcode);
        return result;
    }

    @GetMapping("/aptcode/{aptcode}")
    public HouseInfo getHouseInfo(@PathVariable Long aptcode) {
        return houseInfoService.findByAptCode(aptcode);
    }
}