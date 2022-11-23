package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.entity.Board;
import com.ssafy.happyhouse.domain.enumurate.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Board save(Board board);

    Page<Board> findByBoardTypeOrderByIdDesc(BoardType boardType, Pageable pageable);

    Page<Board> findByTitleLikeOrderByIdDesc(String title, Pageable pageable);

    Optional<Board> findById(Long id);

    @Transactional
    void deleteById(Long boardId);
}
