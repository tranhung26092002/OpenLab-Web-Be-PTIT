package com.example.webtoeic.DTO;

import java.util.List;

public class BaiNgoaiViWithVideoDTO {
    private NgoaiViWithVideoDTO ngoaiViWithVideoDTO;
    private List<VideoBaiNgoaiViResponseDTO> videos;

    public BaiNgoaiViWithVideoDTO() {
    }

    public BaiNgoaiViWithVideoDTO(NgoaiViWithVideoDTO ngoaiViWithVideoDTO, List<VideoBaiNgoaiViResponseDTO> videos) {
        this.ngoaiViWithVideoDTO = ngoaiViWithVideoDTO;
        this.videos = videos;
    }

    public NgoaiViWithVideoDTO getNgoaiViWithVideoDTO() {
        return ngoaiViWithVideoDTO;
    }

    public void setNgoaiViWithVideoDTO(NgoaiViWithVideoDTO ngoaiViWithVideoDTO) {
        this.ngoaiViWithVideoDTO = ngoaiViWithVideoDTO;
    }

    public List<VideoBaiNgoaiViResponseDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoBaiNgoaiViResponseDTO> videos) {
        this.videos = videos;
    }
}
