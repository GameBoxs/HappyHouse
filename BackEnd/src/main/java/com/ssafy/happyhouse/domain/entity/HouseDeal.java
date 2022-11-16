package com.ssafy.happyhouse.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HouseDeal {

    @Id
    @Column(name = "houseDeal_id")
    private Long id;

    private String dealAmount;

    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;
    private String floor;
    private String cancelDealType;

    @JoinColumn(name = "aptCode")
    @ManyToOne(fetch = FetchType.LAZY)
    private HouseInfo houseInfo;
}
