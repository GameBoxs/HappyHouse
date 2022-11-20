package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.entity.FavoriteHouse;
import com.ssafy.happyhouse.domain.entity.HouseInfo;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.repository.FavoriteHouseRepository;
import com.ssafy.happyhouse.repository.HouseInfoRepository;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteHouseService {
    private final FavoriteHouseRepository favoriteHouseRepository;
    private final HouseInfoRepository houseInfoRepository;
    private final UserRepository userRepository;

    public void save(Long userId, Long aptCode) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("NO USER"));
        HouseInfo house = houseInfoRepository.findByAptCode(aptCode)
                .orElseThrow(() -> new IllegalArgumentException("NO HOUSE"));

        FavoriteHouse saveInfo = FavoriteHouse.builder()
                .user(user)
                .houseInfo(house)
                .build();
        favoriteHouseRepository.save(saveInfo);
    }

    public Long findFavoriteCount(Long aptCode) {
        return favoriteHouseRepository.findByHouseInfo_AptCode(aptCode).stream()
                .count();
    }

    public boolean isFavorite(Long userId, Long aptCode) {
        return favoriteHouseRepository.existsByUser_IdAndHouseInfo_AptCode(userId, aptCode);
    }

    public List<HouseInfo> findFavoriteHouses(Long userId) {
        return favoriteHouseRepository.findByUser_Id(userId).stream()
                .map(favorite -> favorite.getHouseInfo())
                .collect(Collectors.toList());
    }

//    public List<FavoriteRankDTO> findRank() {
//        return favoriteHouseRepository.findRank().stream()
//                .limit(10)
//                .collect(Collectors.toList());
//    }
}
