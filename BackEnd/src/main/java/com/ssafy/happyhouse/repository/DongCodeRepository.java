package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.entity.DongCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DongCodeRepository extends JpaRepository<DongCode, String> {
    @Query("select distinct d.gugunName from DongCode d where d.sidoName like :sido% and d.gugunName is not null")
    List<String> findGugunNames(@Param("sido") String sido);

    @Query("select distinct d.dongName from DongCode d where d.sidoName = :sido and d.gugunName = :gugun and d.dongName is not null")
    List<String> findDongName(@Param("sido") String sido, @Param("gugun") String gugun);

    List<DongCode> findAllBySidoNameAndGugunNameAndDongName(String sidoName, String gugunName, String dongName);
}
