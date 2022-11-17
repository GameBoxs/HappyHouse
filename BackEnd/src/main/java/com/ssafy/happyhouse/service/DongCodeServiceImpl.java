package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.DongDTO;
import com.ssafy.happyhouse.repository.DongCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DongCodeServiceImpl implements DongCodeService {

    private final DongCodeRepository dongCodeRepository;

    @Override
    public List<String> findGugun(String sido) {
        List<String> debug = dongCodeRepository.findGugunNames(sido);
        return debug;
    }

    @Override
    public List<String> findDong(String sido, String gugun) {
        return dongCodeRepository.findDongName(sido, gugun);
    }

    @Override
    public List<DongDTO> findDongCode(String sido, String gugun, String dong) {
        return dongCodeRepository.findAllBySidoNameAndGugunNameAndDongName(sido, gugun, dong).stream()
                .map(dongCode -> DongDTO.builder()
                        .dongCode(dongCode.getDongcode())
                        .name(dongCode.getSidoName() + " " + dongCode.getGugunName() + " " + dongCode.getDongName())
                        .build())
                .collect(Collectors.toList());
    }
}
