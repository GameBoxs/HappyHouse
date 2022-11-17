package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.DongDTO;
import com.ssafy.happyhouse.repository.DongCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DongCodeServiceImpl implements DongCodeService {

    private final DongCodeRepository dongCodeRepository;

    @Override
    public List<DongDTO> findGugun(String dongCode) {
        return dongCodeRepository.findGugunNames(dongCode).stream()
                .map(each -> DongDTO.builder()
                        .dongCode(each.getDongcode().substring(0, 4))
                        .name(each.getSidoName() + " " + each.getGugunName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<DongDTO> findDong(String dongCode) {
        return dongCodeRepository.findDongName(dongCode).stream()
                .map(each -> DongDTO.builder()
                        .dongCode(each.getDongcode().substring(0, 6))
                        .name(each.getSidoName() + " " + each.getGugunName() + " " + each.getDongName())
                        .build())
                .collect(Collectors.toList());
    }
}
