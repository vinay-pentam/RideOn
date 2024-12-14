package com.sbpcrs.project.uber.uberapp.services;

import com.sbpcrs.project.uber.uberapp.DTO.DriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.OnboardDriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.SignUpDTO;
import com.sbpcrs.project.uber.uberapp.DTO.UserDTO;

public interface AuthService {

    UserDTO signUp(SignUpDTO signUpDTO);

    String[] login(String email, String password);

    DriverDTO onBoardDriver(Long userId, String vehicleId);

    String[] refreshToken(String refreshToken);
}
