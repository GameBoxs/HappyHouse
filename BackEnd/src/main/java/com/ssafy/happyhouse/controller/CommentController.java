package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.annotation.Login;
import com.ssafy.happyhouse.domain.dto.CommentPageDTO;
import com.ssafy.happyhouse.domain.dto.CommentSaveDTO;
import com.ssafy.happyhouse.domain.dto.PageInfo;
import com.ssafy.happyhouse.domain.entity.Comment;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.enumurate.Role;
import com.ssafy.happyhouse.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public String saveComment(@RequestBody CommentSaveDTO commentSaveDTO, @Login User user) {
        commentService.save(commentSaveDTO, user);
        return "ok";
    }

    @GetMapping("/boards/{boardId}")
    public Page<CommentPageDTO> getBoardComments(@PathVariable Long boardId, @Login User user, @RequestParam(required = false, defaultValue = "0") int page) {
        return commentService.findByBoard(boardId, new PageInfo(page))
                .map(comment -> getCommentPageDTO(user, comment));
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @Login User user) {
        commentService.deleteById(commentId, user);
        return "ok";
    }

    private CommentPageDTO getCommentPageDTO(User user, Comment comment) {
        boolean isDelete = false;
        if (user == null) {
            isDelete = false;
        } else {
            isDelete = comment.getUser().equals(user) || user.getRole() == Role.ADMIN;
        }

        return CommentPageDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writeUserName(comment.getUser().getName())
                .createTime(comment.getCreateTime())
                .isDelete(isDelete)
                .build();
    }
}
