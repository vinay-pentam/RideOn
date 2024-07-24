package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.DriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.SignUpDTO;
import com.sbpcrs.project.uber.uberapp.DTO.UserDTO;
import com.sbpcrs.project.uber.uberapp.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        return null;
    }

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public DriverDTO onBoardDriver(Long userId) {
        return null;
    }
}
