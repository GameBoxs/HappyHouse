package com.ssafy.happyhouse.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Visit {
    @Id
    @GeneratedValue
    @Column(name = "visit_id")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Board board;
}
