package com.ssafy.happyhouse.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseInfo {

    @Id
    @GeneratedValue
    @Column(name = "aptCode")
    private Long aptCode;

    private int buildYear;
    private String roadName;
    private String roadNameBonbun;
    private String roadNameBubun;
    private String roadNameSeq;
    private String roadNameBasementCode;
    private String roadNameCode;
    private String dong;
    private String bonbun;
    private String bubun;
    private String sigunguCode;
    private String eubmyundongCode;

    @ManyToOne
    private DongCode dongCode;

    private String landCode;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;
}
