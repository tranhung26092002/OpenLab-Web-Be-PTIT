package com.example.webtoeic.DTO;

public class RegisterRequestDTO {

   private String email;
   private String password;
   private int vaiTro;



    public RegisterRequestDTO(){

    }

    public RegisterRequestDTO(String email, String password, int vaiTro) {
        this.email = email;
        this.password = password;
        this.vaiTro = vaiTro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }
}
