package com.example.webtoeic.DTO;

import java.util.List;

public class BaiGrammarWithVideoDTO {
    private GrammarWithVideoDTO grammarWithVideoDTO;
    private List<VideoBaiGrammarResponseDTO> videos;

    public BaiGrammarWithVideoDTO() {
    }

    public BaiGrammarWithVideoDTO(GrammarWithVideoDTO grammarWithVideoDTO, List<VideoBaiGrammarResponseDTO> videos) {
        this.grammarWithVideoDTO = grammarWithVideoDTO;
        this.videos = videos;
    }

    public GrammarWithVideoDTO getGrammarWithVideoDTO() {
        return grammarWithVideoDTO;
    }

    public void setGrammarWithVideoDTO(GrammarWithVideoDTO grammarWithVideoDTO) {
        this.grammarWithVideoDTO = grammarWithVideoDTO;
    }

    public List<VideoBaiGrammarResponseDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoBaiGrammarResponseDTO> videos) {
        this.videos = videos;
    }
}
