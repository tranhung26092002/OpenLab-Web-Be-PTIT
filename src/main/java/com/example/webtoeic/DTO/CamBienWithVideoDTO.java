package com.example.webtoeic.DTO;

public class CamBienWithVideoDTO {
    private int camBienId;
    private String tenBaiCamBien;

    public CamBienWithVideoDTO() {
    }

    public CamBienWithVideoDTO(int camBienId, String tenBaiCamBien) {
        this.camBienId = camBienId;
        this.tenBaiCamBien = tenBaiCamBien;
    }

    public int getCamBienId() {
        return camBienId;
    }

    public void setCamBienId(int camBienId) {
        this.camBienId = camBienId;
    }

    public String getTenBaiCamBien() {
        return tenBaiCamBien;
    }

    public void setTenBaiCamBien(String tenBaiCamBien) {
        this.tenBaiCamBien = tenBaiCamBien;
    }
}
