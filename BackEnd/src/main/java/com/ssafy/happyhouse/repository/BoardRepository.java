package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.entity.Board;
import com.ssafy.happyhouse.domain.enumurate.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Board save(Board board);

    List<Board> findByBoardType(BoardType boardType);

    List<Board> findByTitleLike(String title);

    Optional<Board> findById(Long id);
}
