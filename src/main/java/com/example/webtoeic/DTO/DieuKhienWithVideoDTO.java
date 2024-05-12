package com.example.webtoeic.DTO;

public class DieuKhienWithVideoDTO {
    private int dieuKhienId;
    private String tenBaiDieuKhien;

    public DieuKhienWithVideoDTO() {
    }

    public DieuKhienWithVideoDTO(int dieuKhienId, String tenBaiDieuKhien) {
        this.dieuKhienId = dieuKhienId;
        this.tenBaiDieuKhien = tenBaiDieuKhien;
    }

    public int getDieuKhienId() {
        return dieuKhienId;
    }

    public void setDieuKhienId(int dieuKhienId) {
        this.dieuKhienId = dieuKhienId;
    }

    public String getTenBaiDieuKhien() {
        return tenBaiDieuKhien;
    }

    public void setTenBaiDieuKhien(String tenBaiDieuKhien) {
        this.tenBaiDieuKhien = tenBaiDieuKhien;
    }
}
