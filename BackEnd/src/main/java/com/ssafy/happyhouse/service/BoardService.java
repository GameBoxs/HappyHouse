package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.BoardDTO;
import com.ssafy.happyhouse.domain.dto.BoardRegistDTO;
import com.ssafy.happyhouse.domain.dto.BoardUpdateDTO;
import com.ssafy.happyhouse.domain.dto.PageInfo;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.enumurate.BoardType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
    Page<BoardDTO> findAllByType(BoardType boardType, PageInfo pageInfo);

    Page<BoardDTO> findMyBoard(BoardType boardType, User user, PageInfo pageInfo);

    BoardDTO findByBoardId(Long userId, Long BoardId);

    Long save(Long userId, BoardRegistDTO boardRegistDTO);

    BoardDTO updateBoard(User updateUser, BoardUpdateDTO boardDTO);

    void deleteBoard(User deleteUser, Long boardId);

    Page<BoardDTO> findByTitle(String title, PageInfo pageInfo);
}
