package com.example.webtoeic.DTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDTO implements UserDetails {
    private int id;
    private String diaChi;
    private String email;
    private String hoTen;
    private String password;
    private String soDienThoai;
    private int vaiTro;
    private CommentGrammarDTO commentGrammarDTO;

    public CommentGrammarDTO getCommentGrammarDTO() {
        return commentGrammarDTO;
    }

    public void setCommentGrammarDTO(CommentGrammarDTO commentGrammarDTO) {
        this.commentGrammarDTO = commentGrammarDTO;
    }

    public enum Role{
        ROLE_USER, ROLE_ADMIN
    }

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDTO(){

    }

    public UserDTO(int id, String diaChi, String email, String hoTen, String password, String soDienThoai, int vaiTro) {
        this.id = id;
        this.diaChi = diaChi;
        this.email = email;
        this.hoTen = hoTen;
        this.password = password;
        this.soDienThoai = soDienThoai;
        setVaiTro(vaiTro);
    }

    public void setVaiTro(int vaiTro){
        this.vaiTro = vaiTro;
        switch (vaiTro) {
            case 1:
                this.role = Role.ROLE_USER;
                break;
            case 2:
                this.role = Role.ROLE_ADMIN;
                break;
            default:
                throw new IllegalArgumentException("Invalid vaTro value");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getUsername(){
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }



    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
