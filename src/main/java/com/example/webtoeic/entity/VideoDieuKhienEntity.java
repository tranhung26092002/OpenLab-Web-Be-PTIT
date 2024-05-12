package com.example.webtoeic.entity;

import com.example.webtoeic.entity.keys.VideoDieuKhienId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "video_dieu_khien")
public class VideoDieuKhienEntity implements Serializable {
    @EmbeddedId
    private VideoDieuKhienId id;

    @MapsId("videoId")
    @ManyToOne
    @JoinColumn(name="video_id")
    private VideoEntity video;

    @MapsId("dieuKhienId")
    @ManyToOne
    @JoinColumn(name="dieu_khien_id")
    private KhoiDieuKhienEntity khoiDieuKhienEntity;

    public VideoDieuKhienEntity() {
    }

    public VideoDieuKhienEntity(VideoDieuKhienId id, VideoEntity video, KhoiDieuKhienEntity khoiDieuKhienEntity) {
        this.id = id;
        this.video = video;
        this.khoiDieuKhienEntity = khoiDieuKhienEntity;
    }

    public VideoDieuKhienId getId() {
        return id;
    }

    public void setId(VideoDieuKhienId id) {
        this.id = id;
    }

    public VideoEntity getVideo() {
        return video;
    }

    public void setVideo(VideoEntity video) {
        this.video = video;
    }

    public KhoiDieuKhienEntity getKhoiDieuKhienEntity() {
        return khoiDieuKhienEntity;
    }

    public void setKhoiDieuKhienEntity(KhoiDieuKhienEntity khoiDieuKhienEntity) {
        this.khoiDieuKhienEntity = khoiDieuKhienEntity;
    }
}
