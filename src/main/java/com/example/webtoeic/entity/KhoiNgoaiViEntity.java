package com.example.webtoeic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "khoi_ngoai_vi")
public class KhoiNgoaiViEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ngoai_vi_id")
    private int ngoaiViId;

    @Column(name = "anh_ngoai_vi")
    private String anhNgoaiVi;

    @Column(columnDefinition = "MEDIUMTEXT", name = "content_html")
    private String contentHtml;

    @Column(columnDefinition = "LONGTEXT", name = "content_mark_down")
    private String contentMarkDown;

    @Column(name = "ten_ngoai_vi")
    private String tenNgoaiVi;

    @JsonIgnore
    @OneToMany(mappedBy = "khoiNgoaiViEntity")
    private List<VideoNgoaiViEntity> ngoaiViEntities = new ArrayList<>();

    public KhoiNgoaiViEntity() {
    }

    public KhoiNgoaiViEntity(int ngoaiViId, String anhNgoaiVi, String contentHtml, String contentMarkDown, String tenNgoaiVi, List<VideoNgoaiViEntity> ngoaiViEntities) {
        this.ngoaiViId = ngoaiViId;
        this.anhNgoaiVi = anhNgoaiVi;
        this.contentHtml = contentHtml;
        this.contentMarkDown = contentMarkDown;
        this.tenNgoaiVi = tenNgoaiVi;
        this.ngoaiViEntities = ngoaiViEntities;
    }

    public int getNgoaiViId() {
        return ngoaiViId;
    }

    public void setNgoaiViId(int ngoaiViId) {
        this.ngoaiViId = ngoaiViId;
    }

    public String getAnhNgoaiVi() {
        return anhNgoaiVi;
    }

    public void setAnhNgoaiVi(String anhNgoaiVi) {
        this.anhNgoaiVi = anhNgoaiVi;
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

    public String getTenNgoaiVi() {
        return tenNgoaiVi;
    }

    public void setTenNgoaiVi(String tenNgoaiVi) {
        this.tenNgoaiVi = tenNgoaiVi;
    }

    public List<VideoNgoaiViEntity> getNgoaiViEntities() {
        return ngoaiViEntities;
    }

    public void setNgoaiViEntities(List<VideoNgoaiViEntity> ngoaiViEntities) {
        this.ngoaiViEntities = ngoaiViEntities;
    }
}
