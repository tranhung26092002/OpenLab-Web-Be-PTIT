package com.example.webtoeic.DTO;

import java.util.List;

public class BaiCamBienWithVideoDTO {
    private CamBienWithVideoDTO camBienWithVideoDTO;
    private List<VideoBaiCamBienResponseDTO> videos;

    public BaiCamBienWithVideoDTO() {
    }

    public BaiCamBienWithVideoDTO(CamBienWithVideoDTO camBienWithVideoDTO, List<VideoBaiCamBienResponseDTO> videos) {
        this.camBienWithVideoDTO = camBienWithVideoDTO;
        this.videos = videos;
    }

    public CamBienWithVideoDTO getCamBienWithVideoDTO() {
        return camBienWithVideoDTO;
    }

    public void setCamBienWithVideoDTO(CamBienWithVideoDTO camBienWithVideoDTO) {
        this.camBienWithVideoDTO = camBienWithVideoDTO;
    }

    public List<VideoBaiCamBienResponseDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoBaiCamBienResponseDTO> videos) {
        this.videos = videos;
    }
}
