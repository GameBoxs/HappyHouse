package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.dto.BoardDTO;
import com.ssafy.happyhouse.domain.dto.BoardRegistDTO;
import com.ssafy.happyhouse.domain.enumurate.BoardType;
import com.ssafy.happyhouse.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardDTO> boardList(@RequestParam BoardType boardType) {
        log.info("board type = {}", boardType);
        List<BoardDTO> boards = boardService.findAllByType(boardType);
        return boards;
    }

    @PostMapping
    public void saveBoard(@Validated BoardRegistDTO boardRegistDTO, @RequestParam Long userId) throws IOException {
        log.info("boardRegistDTO = {}", boardRegistDTO);
        Long boardId = boardService.save(userId, boardRegistDTO);
    }

    @GetMapping("/{boardId}")
    public BoardDTO findBoardById(@PathVariable Long boardId, @RequestParam(required = false) Long userId) {
        log.info("boardID = {}, userId ={}", boardId, userId);
        return boardService.findByBoardId(userId, boardId);
    }
}
