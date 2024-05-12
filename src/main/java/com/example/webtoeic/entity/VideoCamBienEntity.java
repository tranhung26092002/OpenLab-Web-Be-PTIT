package com.example.webtoeic.entity;

import com.example.webtoeic.entity.keys.VideoCamBienId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "video_cam_bien")
public class VideoCamBienEntity implements Serializable {
    @EmbeddedId
    private VideoCamBienId id;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "video_id")
    private VideoEntity video;

    @ManyToOne
    @MapsId("camBienId")
    @JoinColumn(name ="cam_bien_id")
    private KhoiCamBienEntity khoiCamBienEntity;

    public VideoCamBienEntity() {
    }

    public VideoCamBienEntity(VideoCamBienId id, VideoEntity video, KhoiCamBienEntity khoiCamBienEntity) {
        this.id = id;
        this.video = video;
        this.khoiCamBienEntity = khoiCamBienEntity;
    }

    public VideoCamBienId getId() {
        return id;
    }

    public void setId(VideoCamBienId id) {
        this.id = id;
    }

    public VideoEntity getVideo() {
        return video;
    }

    public void setVideo(VideoEntity video) {
        this.video = video;
    }

    public KhoiCamBienEntity getKhoiCamBienEntity() {
        return khoiCamBienEntity;
    }

    public void setKhoiCamBienEntity(KhoiCamBienEntity khoiCamBienEntity) {
        this.khoiCamBienEntity = khoiCamBienEntity;
    }
}
