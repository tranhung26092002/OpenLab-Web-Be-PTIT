package com.example.webtoeic.entity;

import com.example.webtoeic.entity.keys.VideoBaiGrammarId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "video_bai_grammar")
public class VideoBaiGrammarEntity implements Serializable {
    @EmbeddedId
    private VideoBaiGrammarId id;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "video_id")
    private VideoEntity video;

    @ManyToOne
    @MapsId("baiGrammarId")
    @JoinColumn(name = "baigrammarid")
    private BaiGrammar baiGrammar;

    public VideoBaiGrammarEntity() {
    }

    public VideoBaiGrammarEntity(VideoBaiGrammarId id, VideoEntity video, BaiGrammar baiGrammar) {
        this.id = id;
        this.video = video;
        this.baiGrammar = baiGrammar;
    }

    public VideoBaiGrammarId getId() {
        return id;
    }

    public void setId(VideoBaiGrammarId id) {
        this.id = id;
    }

    public VideoEntity getVideo() {
        return video;
    }

    public void setVideo(VideoEntity video) {
        this.video = video;
    }

    public BaiGrammar getBaiGrammar() {
        return baiGrammar;
    }

    public void setBaiGrammar(BaiGrammar baiGrammar) {
        this.baiGrammar = baiGrammar;
    }
}
