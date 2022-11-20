package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.entity.HouseDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseDealRepository extends JpaRepository<HouseDeal, Long> {

    @Query("select d from HouseDeal d join fetch d.houseInfo i join fetch i.dongCode c where c.dongcode = :dongCode")
    List<HouseDeal> findAllDeal(@Param("dongCode") String dongCode);

    List<HouseDeal> findAllByHouseInfo_AptCode(Long aptCode);
}
