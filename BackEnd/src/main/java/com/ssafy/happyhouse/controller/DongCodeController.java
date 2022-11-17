package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.dto.DongDTO;
import com.ssafy.happyhouse.service.DongCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dongcode")
public class DongCodeController {

    private final DongCodeService dongCodeService;

    @GetMapping("/{sidoName}")
    public List<String> getGugun(@PathVariable String sidoName) {
        return dongCodeService.findGugun(sidoName);
    }

    @GetMapping("/{sidoName}/{gugunName}")
    public List<String> getDong(@PathVariable String sidoName, @PathVariable String gugunName) {
        return dongCodeService.findDong(sidoName, gugunName);
    }

    @GetMapping("/{sidoName}/{gugunName}/{dongName}")
    public List<DongDTO> getDongCode(@PathVariable String sidoName, @PathVariable String gugunName, @PathVariable String dongName) {
        return dongCodeService.findDongCode(sidoName, gugunName, dongName);
    }
}
