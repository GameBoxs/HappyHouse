package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.annotation.Login;
import com.ssafy.happyhouse.domain.dto.FavoriteCountDTO;
import com.ssafy.happyhouse.domain.entity.HouseInfo;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.service.FavoriteHouseService;
import com.ssafy.happyhouse.service.UserService;
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
    private final UserService userService;

    @GetMapping("/{aptCode}")
    public FavoriteCountDTO favoriteCount(@PathVariable Long aptCode) {
        Long cnt = favoriteHouseService.findFavoriteCount(aptCode);
        return FavoriteCountDTO.builder()
                .cnt(cnt)
                .aptCode(aptCode)
                .build();
    }

    @PostMapping("/{aptCode}")
    public void addFavorite(@PathVariable Long aptCode, @Login User user) {
        favoriteHouseService.save(user.getId(), aptCode);
    }

    @GetMapping("/users")
    public List<HouseInfo> findFavorite(@Login User user) {
        return favoriteHouseService.findFavoriteHouses(user.getId());
    }
}
