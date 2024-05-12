package com.example.webtoeic.service;

import com.example.webtoeic.entity.User;
import com.example.webtoeic.repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepositoty userRepositoty;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User customUser = userRepositoty.findByEmail(email);
        if(customUser == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(customUser.getEmail(), customUser.getPassword(), new ArrayList<>());
    }


}