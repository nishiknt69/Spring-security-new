package com.example.securityApp.securityApplicationo.controllers;

import com.example.securityApp.securityApplicationo.dto.LoginDTO;
import com.example.securityApp.securityApplicationo.dto.LoginResponseDTO;
import com.example.securityApp.securityApplicationo.dto.SignUpDTO;
import com.example.securityApp.securityApplicationo.dto.UserDTO;
import com.example.securityApp.securityApplicationo.entities.User;
import com.example.securityApp.securityApplicationo.service.AuthService;
import com.example.securityApp.securityApplicationo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
//        List<UserDTO> users = allUsers.stream()
//                .map(user -> modelMapper.map(user, UserDTO.class))
//                .toList();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = userService.singUp(signUpDTO);

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,
                                                  HttpServletResponse response){
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);

        Cookie cookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request){
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the cookies"));

        LoginResponseDTO loginResponseDTO = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDTO);
    }

}
