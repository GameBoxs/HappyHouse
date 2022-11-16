package com.ssafy.happyhouse.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FavoriteHouse extends TimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "favorite_house_id")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private HouseInfo houseInfo;
}
