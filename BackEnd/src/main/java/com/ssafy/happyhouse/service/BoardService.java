package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.BoardDTO;
import com.ssafy.happyhouse.domain.dto.BoardRegistDTO;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.enumurate.BoardType;

import java.util.List;

public interface BoardService {
    List<BoardDTO> findAllByType(BoardType boardType);

    BoardDTO findByBoardId(Long userId, Long BoardId);

    Long save(Long userId, BoardRegistDTO boardRegistDTO);

    BoardDTO updateBoard(User updateUser, BoardDTO boardDTO);
}
