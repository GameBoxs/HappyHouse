package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.CommentSaveDTO;
import com.ssafy.happyhouse.domain.dto.PageInfo;
import com.ssafy.happyhouse.domain.entity.Board;
import com.ssafy.happyhouse.domain.entity.Comment;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.enumurate.Role;
import com.ssafy.happyhouse.exception.AuthenticationRequiredException;
import com.ssafy.happyhouse.exception.NoUserException;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.CommentRepository;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public Comment save(CommentSaveDTO commentSaveDTO, User user) {
        Board board = boardRepository.findById(commentSaveDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("보드가 없습니다."));
        Comment comment = Comment.builder()
                .user(user)
                .board(board)
                .content(commentSaveDTO.getContent())
                .build();
        return commentRepository.save(comment);
    }

    public Page<Comment> findByBoard(Long boardId, PageInfo pageInfo) {
        return commentRepository.findByBoard_IdOrderByIdDesc(boardId, PageRequest.of(pageInfo.getPage(), pageInfo.getSIZE()));
    }

    public void deleteById(Long id, User user) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        if (user.getRole() != Role.ADMIN && !comment.getUser().equals(user)) {
            throw new AuthenticationRequiredException("댓글 삭제 권한이 없습니다.");
        }
        commentRepository.deleteById(id);
    }
}
