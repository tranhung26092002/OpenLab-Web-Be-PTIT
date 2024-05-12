package com.example.webtoeic.service.Impl;

import com.example.webtoeic.DTO.UserDTO;
import com.example.webtoeic.entity.User;
import com.example.webtoeic.repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepositoty userRepositoty;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepositoty.findByEmail(email);
        if(userEntity == null){
            throw new UsernameNotFoundException("Không tim thấy người dùng với email: " + email);
        }
        return toUserDTO(userEntity);
    }

    private UserDTO toUserDTO(User userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setDiaChi(userEntity.getDiaChi());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setHoTen(userEntity.getHoTen());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setSoDienThoai(userEntity.getSoDienThoai());
        userDTO.setVaiTro(userEntity.getVaiTro());
        return userDTO;
    }
}
