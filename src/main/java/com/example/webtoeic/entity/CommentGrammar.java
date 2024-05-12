package com.example.webtoeic.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comment_grammar")
public class CommentGrammar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmtgrammarid")
    private int cmtgrammarid;

    @Column(name = "cmtgrammarcontent")
    private String cmtgrammarcontent;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "baigrammarid")
    private BaiGrammar baiGrammar;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public int getCmtgrammarid() {
        return cmtgrammarid;
    }

    public void setCmtgrammarid(int cmtgrammarid) {
        this.cmtgrammarid = cmtgrammarid;
    }

    public String getCmtgrammarcontent() {
        return cmtgrammarcontent;
    }

    public void setCmtgrammarcontent(String cmtgrammarcontent) {
        this.cmtgrammarcontent = cmtgrammarcontent;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public BaiGrammar getBaiGrammar() {
        return baiGrammar;
    }

    public void setBaiGrammar(BaiGrammar baiGrammar) {
        this.baiGrammar = baiGrammar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
