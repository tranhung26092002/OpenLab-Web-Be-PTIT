package com.example.webtoeic.entity.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VideoCamBienId implements Serializable {
    @Column(name = "video_id")
    private int videoId;

    @Column(name = "cam_bien_id")
    private int camBienId;

    public VideoCamBienId() {
    }

    public VideoCamBienId(int videoId, int camBienId) {
        this.videoId = videoId;
        this.camBienId = camBienId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getCamBienId() {
        return camBienId;
    }

    public void setCamBienId(int camBienId) {
        this.camBienId = camBienId;
    }
}
