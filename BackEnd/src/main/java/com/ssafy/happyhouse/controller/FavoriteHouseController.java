package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.dto.FavoriteCountDTO;
import com.ssafy.happyhouse.service.FavoriteHouseService;
import com.ssafy.happyhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/aptCode")
    public void addFavorite(@PathVariable Long aptCode, @RequestParam Long userId) {
        favoriteHouseService.save(userId, aptCode);
    }
}