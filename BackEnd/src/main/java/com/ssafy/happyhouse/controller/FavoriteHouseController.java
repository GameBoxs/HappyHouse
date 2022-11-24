package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.annotation.Login;
import com.ssafy.happyhouse.domain.dto.FavoriteCountDTO;
import com.ssafy.happyhouse.domain.dto.FavoriteRankDTO;
import com.ssafy.happyhouse.domain.entity.HouseInfo;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.exception.AuthenticationRequiredException;
import com.ssafy.happyhouse.service.FavoriteHouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteHouseController {

    private final FavoriteHouseService favoriteHouseService;

    @GetMapping("/{aptCode}")
    public FavoriteCountDTO favoriteCount(@PathVariable Long aptCode) {
        Long cnt = favoriteHouseService.findFavoriteCount(aptCode);
        return FavoriteCountDTO.builder()
                .cnt(cnt)
                .aptCode(aptCode)
                .build();
    }

    @PostMapping("/{aptCode}")
    public String addFavorite(@PathVariable Long aptCode, @Login User user) {
        favoriteHouseService.save(user.getId(), aptCode);
        return "ok";
    }

    @DeleteMapping("/{aptCode}")
    public String deleteFavorite(@PathVariable Long aptCode, @Login User user) {
        favoriteHouseService.deleteById(aptCode, user);
        return "ok";
    }

    @GetMapping("/users")
    public List<HouseInfo> findFavorite(@Login User user) {
        if(user == null){
            log.warn("NO USER");
            throw new AuthenticationRequiredException("로그인이 필요합니다.");
        }
        return favoriteHouseService.findFavoriteHouses(user.getId());
    }

    @GetMapping("/rank")
    public List<FavoriteRankDTO> favoriteRank() {
        return favoriteHouseService.findRank();
    }

    @GetMapping("/check/{aptCode}")
    public Boolean isFavorite(@PathVariable Long aptCode, @Login User user) {
        if(user == null){
            log.warn("NO USER");
            throw new AuthenticationRequiredException("로그인이 필요합니다.");
        }
        return favoriteHouseService.isFavorite(user.getId(), aptCode);
    }
}
