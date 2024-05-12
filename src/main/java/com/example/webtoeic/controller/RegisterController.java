package com.example.webtoeic.controller;

import com.example.webtoeic.DTO.RegisterRequestDTO;
import com.example.webtoeic.entity.User;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RegisterController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAllUsers(){
        List<User> listUsers = authenticationService.getAllUsers();
        BaseResponse baseResponse = new BaseResponse();
        try {
            baseResponse.setStatus(200);
            baseResponse.setMessage("Successfully");
            baseResponse.setData(listUsers);
            return ResponseEntity.ok(baseResponse);
        } catch (Exception e){
            baseResponse.setStatus(400);
            baseResponse.setMessage("FAILED");
            return ResponseEntity.ok(baseResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request){
        try {
            User user = authenticationService.register(request.getEmail(), request.getPassword(), request.getVaiTro());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException | IllegalAccessException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable int userId,
                                                   @RequestParam(required = false) String email,
                                                   @RequestParam(required = false) String password,
                                                   @RequestParam(required = false) Integer vaiTro
                                                   ){
        User updateUser = authenticationService.updateUser(userId, email, password, vaiTro);
        BaseResponse baseResponse = new BaseResponse();
        try {
            baseResponse.setStatus(200);
            baseResponse.setMessage("Updated successfully");
            baseResponse.setData(updateUser);
            return ResponseEntity.ok(baseResponse);
        } catch (Exception e){
            baseResponse.setStatus(500);
            baseResponse.setMessage("Update faile: " + e.getMessage());
            return ResponseEntity.badRequest().body(baseResponse);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId){
        BaseResponse baseResponse = authenticationService.deleteUser(userId);
        if(baseResponse.getStatus() == 200){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(baseResponse.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
