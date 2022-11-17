package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.dto.DongDTO;
import com.ssafy.happyhouse.service.DongCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DongCodeController {

    private final DongCodeService dongCodeService;

    @GetMapping("/dongcode/{}")
    public List<DongDTO> getAreaName(@PathVariable String dongCode) {
        if (dongCode.length() == 2) {
            return dongCodeService.findGugun(dongCode);
        }
        return dongCodeService.findGugun(dongCode);
    }
}
