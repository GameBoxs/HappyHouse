package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment save(Comment comment);

    void deleteById(Long commentId);

    Optional<Comment> findById(Long commentId);

    Page<Comment> findByBoard_IdOrderByIdDesc(Long boardId, Pageable pageable);

    Page<Comment> findByUser_IdOrderByIdDesc(Long userId, Pageable pageable);
}
