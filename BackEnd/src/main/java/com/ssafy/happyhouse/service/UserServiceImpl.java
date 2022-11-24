package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.domain.dto.UserDTO;
import com.ssafy.happyhouse.domain.entity.User;
import com.ssafy.happyhouse.domain.enumurate.Role;
import com.ssafy.happyhouse.exception.NoUserException;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void save(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(Role.NORMAL)
                .build();
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserInfo(Long id) {
        Optional<User> find = userRepository.findById(id);
        find.orElseThrow(() -> new NoUserException("NO USER"));

        return UserDTO.builder()
                .id(find.get().getId())
                .name(find.get().getName())
                .email(find.get().getEmail())
                .password(find.get().getPassword())
                .build();
    }

    public void updatePassword(Long userId, String password) {
        User updateUser = userRepository.findById(userId).orElse(null);
        updateUser.changePassword(password);

        userRepository.save(updateUser);
    }

    @Override
    public boolean validateEmail(String email) {
        //없으면 false
        return userRepository.findByEmail(email).orElse(null) != null;
    }

    @Override
    public boolean login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null) != null;
    }
}
