package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.UserDTO;

public interface UserService {
    void save(UserDTO userDTO);

    UserDTO getUserInfo(Long id);
}
