package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.dto.FavoriteRankDTO;
import com.ssafy.happyhouse.domain.entity.FavoriteHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteHouseRepository extends JpaRepository<FavoriteHouse, Long> {

    List<FavoriteHouse> findByHouseInfo_AptCode(Long aptCode);

    boolean existsByUser_IdAndHouseInfo_AptCode(Long userId, Long aptCode);

    FavoriteHouse save(FavoriteHouse favoriteHouse);

    @Query("select HouseInfo.aptCode, count(User) as cnt from FavoriteHouse f group by f.houseInfo order by cnt")
    List<FavoriteRankDTO> findRank();
}
