package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.BoardDTO;
import com.ssafy.happyhouse.domain.dto.BoardRegistDTO;
import com.ssafy.happyhouse.domain.dto.BoardUpdateDTO;
import com.ssafy.happyhouse.domain.dto.PageInfo;
import com.ssafy.happyhouse.domain.entity.Board;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.entity.Visit;
import com.ssafy.happyhouse.domain.enumurate.BoardType;
import com.ssafy.happyhouse.domain.enumurate.Role;
import com.ssafy.happyhouse.exception.AuthenticationRequiredException;
import com.ssafy.happyhouse.exception.NoUserException;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.UserRepository;
import com.ssafy.happyhouse.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    private final UserRepository userRepository;
    private final VisitRepository visitRepository;

    @Override
    public Page<BoardDTO> findAllByType(BoardType boardType, PageInfo pageInfo) {
        Page<Board> notice = boardRepository.findByBoardTypeOrderByIdDesc(boardType, PageRequest.of(pageInfo.getPage(), pageInfo.getSIZE()));
        return notice
                .map(board -> getBoardDTO(board));
    }

    @Override
    public BoardDTO findByBoardId(Long userId, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("NO BOARD"));

        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NoUserException("NO USER"));

            Visit visit = visitRepository.findByBoard_IdAndUser_Id(boardId, userId).orElse(null);
            if (visit == null) {
                visit = Visit.builder()
                        .user(user)
                        .board(board)
                        .build();
                visitRepository.save(visit);
            }
        }

        return getBoardDTO(board);
    }

    @Override
    public Long save(Long userId, BoardRegistDTO boardRegistDTO) {
        Board board = Board.builder()
                .title(boardRegistDTO.getTitle())
                .content(boardRegistDTO.getContent())
                .boardType(boardRegistDTO.getBoardType())
                .user(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new))
                .build();
        return boardRepository.save(board).getId();
    }

    @Override
    public BoardDTO updateBoard(User updateUser, BoardUpdateDTO boardDTO) {
        Board board = boardRepository.findById(boardDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("NO BOARD"));

        if (updateUser.getRole() != Role.ADMIN && !board.getUser().equals(updateUser)) {
            throw new AuthenticationRequiredException("로그인이 필요합니다.");
        }

        board.update(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);

        return getBoardDTO(board);
    }

    @Override
    public void deleteBoard(User deleteUser, Long boardId) {
        Board deletedBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("NO DELETED BOARD"));

        //권한이 없으면 에러 반환
        if (deleteUser.getRole() != Role.ADMIN && !deletedBoard.getUser().equals(deleteUser)) {
            throw new AuthenticationRequiredException("로그인이 필요합니다.");
        }

        visitRepository.deleteAllByBoard_Id(boardId);
        boardRepository.deleteById(boardId);
    }

    @Override
    public Page<BoardDTO> findByTitle(String title, PageInfo pageInfo) {
        return boardRepository.findByTitleLikeOrderByIdDesc(title, PageRequest.of(pageInfo.getPage(), pageInfo.getSIZE()))
                .map(board -> getBoardDTO(board));
    }

    private BoardDTO getBoardDTO(Board board) {
        int hit = visitRepository.findAllByBoard_Id(board.getId()).size();
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .username(board.getUser().getName())
                .createTime(board.getCreateTime())
                .boardType(board.getBoardType())
                .hit(hit)
                .build();
    }
}
