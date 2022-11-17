package com.ssafy.happyhouse.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
