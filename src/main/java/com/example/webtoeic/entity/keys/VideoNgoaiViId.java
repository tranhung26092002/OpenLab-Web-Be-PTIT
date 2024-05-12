package com.example.webtoeic.entity.keys;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VideoNgoaiViId implements Serializable {
    @Column(name = "video_id")
    private int videoId;

    @Column(name = "nooai_vi_id")
    private int ngoaiViId;

    public VideoNgoaiViId() {
    }

    public VideoNgoaiViId(int videoId, int ngoaiViId) {
        this.videoId = videoId;
        this.ngoaiViId = ngoaiViId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getNgoaiViId() {
        return ngoaiViId;
    }

    public void setNgoaiViId(int ngoaiViId) {
        this.ngoaiViId = ngoaiViId;
    }
}
