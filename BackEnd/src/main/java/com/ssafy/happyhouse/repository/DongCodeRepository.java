package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.entity.DongCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DongCodeRepository extends JpaRepository<DongCode, String> {
    @Query("select distinct d.gugunName from DongCode d where d.dongcode like :dongCode%")
    List<DongCode> findGugunNames(String dongCode);

    @Query("select distinct d.dongName from DongCode d where d.dongcode like :dongCode%")
    List<DongCode> findDongName(String dongCode);
}
