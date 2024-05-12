package com.example.webtoeic.entity.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VideoBaiGrammarId implements Serializable {
    @Column(name = "video_id")
    private int videoId;

    @Column(name = "baigrammarid")
    private int baiGrammarId;

    public VideoBaiGrammarId() {
    }

    public VideoBaiGrammarId(int videoId, int baiGrammarId) {
        this.videoId = videoId;
        this.baiGrammarId = baiGrammarId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getBaiGrammarId() {
        return baiGrammarId;
    }

    public void setBaiGrammarId(int baiGrammarId) {
        this.baiGrammarId = baiGrammarId;
    }
}
