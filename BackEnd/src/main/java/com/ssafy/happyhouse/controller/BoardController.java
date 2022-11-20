package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.annotation.Login;
import com.ssafy.happyhouse.domain.dto.BoardDTO;
import com.ssafy.happyhouse.domain.dto.BoardRegistDTO;
import com.ssafy.happyhouse.domain.entity.User;
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

    //TODO : 로그인한 유저만 들어오는 처리

    private final BoardService boardService;

    @GetMapping
    public List<BoardDTO> boardList(@RequestParam BoardType boardType) {
        List<BoardDTO> boards = boardService.findAllByType(boardType);
        return boards;
    }

    @PostMapping
    public void saveBoard(@Validated BoardRegistDTO boardRegistDTO, @Login User user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("NO USER");
        }
        Long boardId = boardService.save(user.getId(), boardRegistDTO);
    }

    @GetMapping("/{boardId}")
    public BoardDTO findBoardById(@PathVariable Long boardId, @Login User user) {
        Long userId =
                user == null
                        ? null
                        : user.getId();

        return boardService.findByBoardId(userId, boardId);
    }

    @PatchMapping("/{boardId}")
    public BoardDTO updateBoard(@PathVariable Long boardId, @ModelAttribute BoardDTO boardDTO, @Login User user) {
        return boardService.updateBoard(user, boardDTO);
    }

}
