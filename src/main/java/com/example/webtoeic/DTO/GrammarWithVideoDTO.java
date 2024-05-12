package com.example.webtoeic.DTO;

public class GrammarWithVideoDTO {
    private int baiGrammarId;
    private String tenBaiGrammar;

    public GrammarWithVideoDTO() {
    }

    public GrammarWithVideoDTO(int baiGrammarId, String tenBaiGrammar) {
        this.baiGrammarId = baiGrammarId;
        this.tenBaiGrammar = tenBaiGrammar;
    }

    public int getBaiGrammarId() {
        return baiGrammarId;
    }

    public void setBaiGrammarId(int baiGrammarId) {
        this.baiGrammarId = baiGrammarId;
    }

    public String getTenBaiGrammar() {
        return tenBaiGrammar;
    }

    public void setTenBaiGrammar(String tenBaiGrammar) {
        this.tenBaiGrammar = tenBaiGrammar;
    }
}
