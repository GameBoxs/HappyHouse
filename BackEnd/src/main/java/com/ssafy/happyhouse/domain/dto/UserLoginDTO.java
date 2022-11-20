package com.ssafy.happyhouse.domain.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserLoginDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
