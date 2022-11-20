package com.ssafy.happyhouse.domain.entity;

import com.ssafy.happyhouse.domain.enumurate.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends TimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    private String name;

    @Column(unique = true)
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotNull
    @Enumerated
    private Role role;

    public void changePassword(String password) {
        this.password = password;
    }
}
