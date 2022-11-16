package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    Visit save(Visit visit);

    List<User> findAllByBoard_Id(Long boardId);
}
