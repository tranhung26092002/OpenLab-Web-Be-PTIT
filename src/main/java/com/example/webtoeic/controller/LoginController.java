package com.example.webtoeic.controller;

import com.example.webtoeic.DTO.AuthenticationResponse;
import com.example.webtoeic.DTO.LoginRequestDTO;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginRequestDTO loginRequest){
        BaseResponse response = new BaseResponse();
        try {
            AuthenticationResponse authResponse = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());
            String token = authResponse.getToken();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Login successfully");
            response.setData(authResponse);
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(response);
        } catch (BadCredentialsException e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Invalid email or password");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
