package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.BoardDTO;
import com.ssafy.happyhouse.domain.dto.BoardRegistDTO;
import com.ssafy.happyhouse.domain.entity.Board;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.entity.Visit;
import com.ssafy.happyhouse.domain.enumurate.BoardType;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.UserRepository;
import com.ssafy.happyhouse.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;
    private final VisitRepository visitRepository;

    @Override
    public List<BoardDTO> findAllByType(BoardType boardType) {
        List<Board> notice = boardRepository.findByBoardType(boardType);
        return notice.stream()
                .map(board -> getBoardDTO(board))
                .collect(Collectors.toList());
    }

    @Override
    public BoardDTO findByBoardId(Long userId, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("NO BOARD"));
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("NO USER"));
            Visit visit = Visit.builder()
                    .user(user)
                    .board(board)
                    .build();
            visitRepository.save(visit);
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

    private BoardDTO getBoardDTO(Board board) {
        int hit = visitRepository.findAllByBoard_Id(board.getId()).size();
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createTime(board.getCreateTime())
                .boardType(board.getBoardType())
                .hit(hit)
                .build();
    }
}
