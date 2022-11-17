package com.ssafy.happyhouse.domain.entity;

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
public class DongCode {
    @Id
    @NotEmpty
    @Column(name = "dongCode")
    private String dongcode;

    @NotEmpty
    private String sidoName;

    @NotEmpty
    private String gugunName;

    @NotEmpty
    private String dongName;
}
