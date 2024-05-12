package com.example.webtoeic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "khoi_cam_bien")
@Builder
@Data
public class KhoiCamBienEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cam_bien_id")
    private int camBienId;

    @Column(name = "anh_cam_bien")
    private String anhCamBien;

    @Column(columnDefinition = "MEDIUMTEXT", name = "content_html")
    private String contentHtml;

    @Column(columnDefinition = "LONGTEXT", name ="content_mark_down")
    private String contentMarkDown;

    @Column(name = "ten_cam_bien")
    private String tenCamBien;

    @JsonIgnore
    @OneToMany(mappedBy = "khoiCamBienEntity")
    private List<VideoCamBienEntity> camBienEntities = new ArrayList<>();

    public KhoiCamBienEntity() {

    }

    public KhoiCamBienEntity(int camBienId, String anhCamBien, String contentHtml, String contentMarkDown, String tenCamBien, List<VideoCamBienEntity> camBienEntities) {
        this.camBienId = camBienId;
        this.anhCamBien = anhCamBien;
        this.contentHtml = contentHtml;
        this.contentMarkDown = contentMarkDown;
        this.tenCamBien = tenCamBien;
        this.camBienEntities = camBienEntities;
    }

    public int getCamBienId() {
        return camBienId;
    }

    public void setCamBienId(int camBienId) {
        this.camBienId = camBienId;
    }

    public String getAnhCamBien() {
        return anhCamBien;
    }

    public void setAnhCamBien(String anhCamBien) {
        this.anhCamBien = anhCamBien;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getContentMarkDown() {
        return contentMarkDown;
    }

    public void setContentMarkDown(String contentMarkDown) {
        this.contentMarkDown = contentMarkDown;
    }

    public String getTenCamBien() {
        return tenCamBien;
    }

    public void setTenCamBien(String tenCamBien) {
        this.tenCamBien = tenCamBien;
    }

    public List<VideoCamBienEntity> getCamBienEntities() {
        return camBienEntities;
    }

    public void setCamBienEntities(List<VideoCamBienEntity> camBienEntities) {
        this.camBienEntities = camBienEntities;
    }

}
