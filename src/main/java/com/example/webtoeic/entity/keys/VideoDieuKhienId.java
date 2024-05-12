package com.example.webtoeic.entity.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VideoDieuKhienId implements Serializable {
    @Column(name = "video_id")
    private int videoId;

    @Column(name = "dieu_khien_id")
    private int dieuKhienId;

    public VideoDieuKhienId() {
    }

    public VideoDieuKhienId(int videoId, int dieuKhienId) {
        this.videoId = videoId;
        this.dieuKhienId = dieuKhienId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getDieuKhienId() {
        return dieuKhienId;
    }

    public void setDieuKhienId(int dieuKhienId) {
        this.dieuKhienId = dieuKhienId;
    }
}
