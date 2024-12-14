package com.sbpcrs.project.uber.uberapp.controllers;

import com.sbpcrs.project.uber.uberapp.DTO.*;
import com.sbpcrs.project.uber.uberapp.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> userSignUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = authService.signUp(signUpDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/onboardNewDriver/{userId}")
    public ResponseEntity<DriverDTO> onboardNewDriver(@PathVariable Long userId, @RequestBody OnboardDriverDTO onboardDriverDTO){
        return new ResponseEntity<>(authService.onBoardDriver(userId, onboardDriverDTO.getVehicleId()),
                HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest httpServletRequest,
                                                  HttpServletResponse httpServletResponse){
        String[] tokens = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        Cookie cookie = new Cookie("token", tokens[1]);
        cookie.setHttpOnly(true);

        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request,HttpServletResponse httpServletResponse){
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() ->new AuthorizationServiceException("Refresh token not found inside the cookies"));

        String[] tokens = authService.refreshToken(refreshToken);
//        Cookie cookie = new Cookie("token", tokens[1]);

        Cookie cookie = Arrays.stream(request.getCookies()).filter(cookie1 -> "token".equals(cookie1.getName())).findFirst()
                .orElseThrow(() ->new AuthorizationServiceException("Refresh token not found inside the cookies"));
        cookie.setHttpOnly(true);
        cookie.setValue(tokens[1]);
        httpServletResponse.addCookie(cookie);


        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));

    }
}
