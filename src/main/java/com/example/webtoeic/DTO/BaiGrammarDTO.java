package com.example.webtoeic.DTO;

import javax.persistence.Column;

public class BaiGrammarDTO {
    private Integer baiGrammarId;
    private String tenBaiGrammar;
    private String anhBaiGrammar;
    private String contentHTML;
    private String contentMarkDown;

    public Integer getBaiGrammarId() {
        return baiGrammarId;
    }

    public void setBaiGrammarId(Integer baiGrammarId) {
        this.baiGrammarId = baiGrammarId;
    }

    public String getTenBaiGrammar() {
        return tenBaiGrammar;
    }

    public void setTenBaiGrammar(String tenBaiGrammar) {
        this.tenBaiGrammar = tenBaiGrammar;
    }

    public String getAnhBaiGrammar() {
        return anhBaiGrammar;
    }

    public void setAnhBaiGrammar(String anhBaiGrammar) {
        this.anhBaiGrammar = anhBaiGrammar;
    }

    public String getContentHTML() {
        return contentHTML;
    }

    public void setContentHTML(String contentHTML) {
        this.contentHTML = contentHTML;
    }

    public String getContentMarkDown() {
        return contentMarkDown;
    }

    public void setContentMarkDown(String contentMarkDown) {
        this.contentMarkDown = contentMarkDown;
    }

    public BaiGrammarDTO(){

    }
    public BaiGrammarDTO(Integer baiGrammarId, String tenBaiGrammar, String anhBaiGrammar, String contentHTML, String contentMarkDown) {
        this.baiGrammarId = baiGrammarId;
        this.tenBaiGrammar = tenBaiGrammar;
        this.anhBaiGrammar = anhBaiGrammar;
        this.contentHTML = contentHTML;
        this.contentMarkDown = contentMarkDown;
    }
}
