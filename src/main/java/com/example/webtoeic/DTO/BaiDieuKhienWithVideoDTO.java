package com.example.webtoeic.DTO;

import java.util.List;

public class BaiDieuKhienWithVideoDTO {
    private DieuKhienWithVideoDTO dieuKhienWithVideoDTO;
    private List<VideoBaiDieuKhienResponseDTO> videos;

    public BaiDieuKhienWithVideoDTO() {
    }

    public BaiDieuKhienWithVideoDTO(DieuKhienWithVideoDTO dieuKhienWithVideoDTO, List<VideoBaiDieuKhienResponseDTO> videos) {
        this.dieuKhienWithVideoDTO = dieuKhienWithVideoDTO;
        this.videos = videos;
    }

    public DieuKhienWithVideoDTO getDieuKhienWithVideoDTO() {
        return dieuKhienWithVideoDTO;
    }

    public void setDieuKhienWithVideoDTO(DieuKhienWithVideoDTO dieuKhienWithVideoDTO) {
        this.dieuKhienWithVideoDTO = dieuKhienWithVideoDTO;
    }

    public List<VideoBaiDieuKhienResponseDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoBaiDieuKhienResponseDTO> videos) {
        this.videos = videos;
    }
}
