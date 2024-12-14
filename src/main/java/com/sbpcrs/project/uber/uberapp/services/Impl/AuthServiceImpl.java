package com.sbpcrs.project.uber.uberapp.services.Impl;

import com.sbpcrs.project.uber.uberapp.DTO.DriverDTO;
import com.sbpcrs.project.uber.uberapp.DTO.SignUpDTO;
import com.sbpcrs.project.uber.uberapp.DTO.UserDTO;
import com.sbpcrs.project.uber.uberapp.entities.Driver;
import com.sbpcrs.project.uber.uberapp.entities.User;
import com.sbpcrs.project.uber.uberapp.entities.enums.Role;
import com.sbpcrs.project.uber.uberapp.exceptions.ResourceNotFoundException;
import com.sbpcrs.project.uber.uberapp.exceptions.RuntimeConflictException;
import com.sbpcrs.project.uber.uberapp.repositories.UserRepository;
import com.sbpcrs.project.uber.uberapp.security.JWTService;
import com.sbpcrs.project.uber.uberapp.services.AuthService;
import com.sbpcrs.project.uber.uberapp.services.DriverService;
import com.sbpcrs.project.uber.uberapp.services.RiderService;
import com.sbpcrs.project.uber.uberapp.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.Array;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper mapper;
    private final RiderService riderService;
    private final UserRepository userRepository;
    private final WalletService walletService;
    private final DriverService driverService;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO signUp(SignUpDTO signUpDTO) {

        boolean exist = userRepository.existsByEmail(signUpDTO.getEmail());
        if(exist) throw new RuntimeConflictException("Cannot create user with given email, user already exits"+signUpDTO.getEmail());

        User user = mapper.map(signUpDTO, User.class);
        user.setRoles(Set.of(Role.RIDER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);


        // creating rider object for user
        riderService.createRider(savedUser);
        // todo create a wallet entity for user
        walletService.createWallet(savedUser);

        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public String[] login(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new String[]{accessToken, refreshToken};
    }

    @Override
    public DriverDTO onBoardDriver(Long userId, String vehicleId) {
       User user = userRepository.findById(userId).orElseThrow(
               ()-> new ResourceNotFoundException("User not found with Id : " +userId)
       );

       if(user.getRoles().contains(Role.DRIVER))
           throw new RuntimeException("Failed to onBoard, the user with id : " + userId + " is already a DRIVER");

        Driver driver = Driver.builder()
                .available(true)
                .vehicleId(vehicleId)
                .user(user)
                .build();
        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(driver);
        return mapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public String[] refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User not found with id : "+ userId
        ));

        String accessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);
        return new String[]{accessToken, newRefreshToken};
    }
}
