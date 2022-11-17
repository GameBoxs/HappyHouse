package com.ssafy.happyhouse.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
