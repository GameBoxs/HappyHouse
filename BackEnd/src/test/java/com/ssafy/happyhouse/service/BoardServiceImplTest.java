package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.BoardDTO;
import com.ssafy.happyhouse.domain.entity.Board;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.entity.Visit;
import com.ssafy.happyhouse.domain.enumurate.BoardType;
import com.ssafy.happyhouse.domain.enumurate.Role;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.VisitRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    @Mock
    BoardRepository boardRepository;

    @Mock
    VisitRepository visitRepository;

    BoardServiceImpl boardService;

    @BeforeEach
    void makeMock() {
        Mockito.doReturn(makeBoards()).when(boardRepository).findByBoardType(BoardType.notice);
        Mockito.doReturn(makeVisits()).when(visitRepository).findAllByBoard_Id(1L);
        boardService = new BoardServiceImpl(boardRepository, null, visitRepository);
    }

    @Test
    void findNoticeList() throws Exception {
        //Given
        //When
        List<BoardDTO> findResult = boardService.findAllByType(BoardType.notice);

        //Then
        Assertions.assertThat(findResult.size()).isEqualTo(2);
    }

    User makeUser() {
        return User.builder()
                .id(1L)
                .name("테스터")
                .email("test@test.com")
                .password("password")
                .role(Role.NORMAL)
                .build();
    }

    List<Visit> makeVisits() {
        Visit visit1 = Visit.builder()
                .id(1L)
                .user(makeUser())
                .board(makeBoard())
                .build();
        return new ArrayList<>(List.of(visit1));
    }

    Board makeBoard() {
        return Board.builder()
                .id(1L)
                .title("test")
                .content("test")
                .boardType(BoardType.notice)
                .build();
    }

    List<Board> makeBoards() {
        Board board1 = Board.builder()
                .id(1L)
                .title("test")
                .content("test")
                .boardType(BoardType.notice)
                .build();

        Board board2 = Board.builder()
                .id(2L)
                .title("test2")
                .content("test2")
                .boardType(BoardType.notice)
                .build();
        return new ArrayList<>(List.of(board1, board2));
    }

}