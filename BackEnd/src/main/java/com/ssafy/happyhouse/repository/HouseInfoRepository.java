package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.entity.DongCode;
import com.ssafy.happyhouse.domain.entity.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {
    List<HouseInfo> findAllByDongCode(DongCode dongCode);

    Optional<HouseInfo> findByAptCode(Long aptCode);
}
