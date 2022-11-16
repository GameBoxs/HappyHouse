package com.ssafy.happyhouse.domain.entity;

import com.ssafy.happyhouse.domain.enumurate.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board extends TimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @Enumerated
    private BoardType boardType;

    @ManyToOne
    private User user;
}
