package com.example.webtoeic.entity;

import com.example.webtoeic.entity.keys.VideoDieuKhienId;
import com.example.webtoeic.entity.keys.VideoNgoaiViId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "video_ngoai_vi")
public class VideoNgoaiViEntity implements Serializable {
    @EmbeddedId
    private VideoNgoaiViId id;

    @MapsId("videoId")
    @ManyToOne
    @JoinColumn(name="video_id")
    private VideoEntity video;

    @MapsId("ngoaiViId")
    @ManyToOne
    @JoinColumn(name="ngoai_vi_id")
    private KhoiNgoaiViEntity khoiNgoaiViEntity;

    public VideoNgoaiViEntity() {
    }

    public VideoNgoaiViEntity(VideoNgoaiViId id, VideoEntity video, KhoiNgoaiViEntity khoiNgoaiViEntity) {
        this.id = id;
        this.video = video;
        this.khoiNgoaiViEntity = khoiNgoaiViEntity;
    }

    public VideoNgoaiViId getId() {
        return id;
    }

    public void setId(VideoNgoaiViId id) {
        this.id = id;
    }

    public VideoEntity getVideo() {
        return video;
    }

    public void setVideo(VideoEntity video) {
        this.video = video;
    }

    public KhoiNgoaiViEntity getKhoiNgoaiViEntity() {
        return khoiNgoaiViEntity;
    }

    public void setKhoiNgoaiViEntity(KhoiNgoaiViEntity khoiNgoaiViEntity) {
        this.khoiNgoaiViEntity = khoiNgoaiViEntity;
    }
}
