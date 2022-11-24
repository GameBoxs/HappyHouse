package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.annotation.Login;
import com.ssafy.happyhouse.domain.dto.BoardDTO;
import com.ssafy.happyhouse.domain.dto.BoardRegistDTO;
import com.ssafy.happyhouse.domain.dto.BoardUpdateDTO;
import com.ssafy.happyhouse.domain.dto.PageInfo;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.enumurate.BoardType;
import com.ssafy.happyhouse.exception.NoUserException;
import com.ssafy.happyhouse.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public Page<BoardDTO> boardList(@RequestParam BoardType boardType, @RequestParam(required = false, defaultValue = "0") Integer page) {
        return boardService.findAllByType(boardType, new PageInfo(page));
    }

    @PostMapping
    public String saveBoard(@Validated @RequestBody BoardRegistDTO boardRegistDTO, @Login User user) {
        boardService.save(user.getId(), boardRegistDTO);
        return "ok";
    }

    @GetMapping("/user")
    public Page<BoardDTO> myBoardList(@RequestParam BoardType boardType, @Login User user, @RequestParam(required = false, defaultValue = "0") Integer page) {
        return boardService.findMyBoard(boardType, user, new PageInfo(page));
    }

    @GetMapping("/search")
    public Page<BoardDTO> searchByTitle(@RequestParam String title, @RequestParam(required = false, defaultValue = "0") Integer page) {
        return boardService.findByTitle(title, new PageInfo(page));
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
    public BoardDTO updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateDTO boardDTO, @Login User user) {
        boardDTO.setId(boardId);
        return boardService.updateBoard(user, boardDTO);
    }

    @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable Long boardId, @Login User user) {
        boardService.deleteBoard(user, boardId);
        return "ok";
    }

}
