package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    Visit save(Visit visit);

    List<Visit> findAllByBoard_Id(Long boardId);

    Optional<Visit> findByBoard_IdAndUser_Id(Long boardId, Long userId);
}
