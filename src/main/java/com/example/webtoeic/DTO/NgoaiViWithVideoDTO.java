package com.example.webtoeic.DTO;

public class NgoaiViWithVideoDTO {
    private int ngoaiViId;
    private String tenBaiNgoaiVi;

    public NgoaiViWithVideoDTO() {
    }

    public NgoaiViWithVideoDTO(int ngoaiViId, String tenBaiNgoaiVi) {
        this.ngoaiViId = ngoaiViId;
        this.tenBaiNgoaiVi = tenBaiNgoaiVi;
    }

    public int getNgoaiViId() {
        return ngoaiViId;
    }

    public void setNgoaiViId(int ngoaiViId) {
        this.ngoaiViId = ngoaiViId;
    }

    public String getTenBaiNgoaiVi() {
        return tenBaiNgoaiVi;
    }

    public void setTenBaiNgoaiVi(String tenBaiNgoaiVi) {
        this.tenBaiNgoaiVi = tenBaiNgoaiVi;
    }
}
