package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.dto.FavoriteRankDTO;
import com.ssafy.happyhouse.domain.entity.FavoriteHouse;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteHouseRepository extends JpaRepository<FavoriteHouse, Long> {

    List<FavoriteHouse> findByHouseInfo_AptCode(Long aptCode);

    boolean existsByUser_IdAndHouseInfo_AptCode(Long userId, Long aptCode);

    FavoriteHouse save(FavoriteHouse favoriteHouse);

    List<FavoriteHouse> findByUser_Id(Long userId);

    @Query(value = "select " +
            "new com.ssafy.happyhouse.domain.dto.FavoriteRankDTO(i.aptCode, i.apartmentName, count(i)) " +
            "from HouseInfo i " +
            "join FavoriteHouse f on f.houseInfo.aptCode = i.aptCode " +
            "group by i.aptCode order by count(i) desc")
    List<FavoriteRankDTO> findRank(Pageable pageable);

    void delete(FavoriteHouse favoriteHouse);

    Optional<FavoriteHouse> findByUser_IdAndHouseInfo_AptCode(Long userId, Long aptCode);
}
