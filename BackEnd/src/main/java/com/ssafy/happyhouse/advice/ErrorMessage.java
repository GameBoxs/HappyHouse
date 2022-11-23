package com.ssafy.happyhouse.advice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

    private String massage;
    private int status;
    private String URI;
}
