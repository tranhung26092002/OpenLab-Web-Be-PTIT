package com.example.webtoeic.controller.admin;

import com.example.webtoeic.DTO.*;
import com.example.webtoeic.entity.BaiGrammar;
import com.example.webtoeic.entity.KhoiCamBienEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.BaiGrammarService;
import com.example.webtoeic.service.KhoiCamBienService;
import com.example.webtoeic.service.VideoBaiCamBienService;
import com.example.webtoeic.service.VideoBaiGrammarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/baicambien")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class VideoCamBienControllerAdmin {
    @Autowired
    private VideoBaiCamBienService videoBaiCamBienService;

    @Autowired
    private KhoiCamBienService khoiCamBienService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("baiCamBienId") int baiCamBienId ) {

        BaseResponse response = videoBaiCamBienService.createVideo(file, title, description, baiCamBienId);


        if (response.getStatus() == 200) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update/{videoId}/{baiCamBienId}")
    public ResponseEntity<BaseResponse> updateVideo(
            @RequestParam(value = "file",required = false) MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @PathVariable("videoId") int videoId,
            @PathVariable("baiCamBienId") int baiCamBienId
    ){
        BaseResponse response = videoBaiCamBienService.updateVideo(file, title, description, videoId, baiCamBienId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete/{videoId}/{baiCamBienId}")
    public ResponseEntity<BaseResponse> deleteVideo(
            @PathVariable("videoId") int videoId,
            @PathVariable("baiCamBienId") int baiCamBienId
    ){
        BaseResponse response = videoBaiCamBienService.deleteVideo(videoId, baiCamBienId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/allWithVideos")
    public ResponseEntity<BaseResponse> getAllBaiCamBienWithVideos(){
        try {
            BaseResponse response = khoiCamBienService.getAllKhoiCamBien();
            if(response.getStatus() != 200){
                throw new RuntimeException("Failed to fetch BaiGrammar");
            }
            List<KhoiCamBienEntity> allBaiCamBien = (List<KhoiCamBienEntity>) response.getData();

            List<BaiCamBienWithVideoDTO> result = new ArrayList<>();
            for (KhoiCamBienEntity khoiCamBienEntity : allBaiCamBien) {
                CamBienWithVideoDTO dto = convertToDTO(khoiCamBienEntity);
                List<VideoBaiCamBienResponseDTO> videos = videoBaiCamBienService.findVideoByBaiCamBienId(khoiCamBienEntity.getCamBienId());

                BaiCamBienWithVideoDTO baiCamBienWithVideoDTO = new BaiCamBienWithVideoDTO();
                baiCamBienWithVideoDTO.setCamBienWithVideoDTO(dto);
                baiCamBienWithVideoDTO.setVideos(videos);
                result.add(baiCamBienWithVideoDTO);
            }

            return ResponseEntity.ok(new BaseResponse(200, "Success", result));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new BaseResponse(500, "Error fetching data", null));
        }
    }

    private CamBienWithVideoDTO convertToDTO(KhoiCamBienEntity khoiCamBienEntity){
        CamBienWithVideoDTO camBienWithVideoDTO = new CamBienWithVideoDTO();
        camBienWithVideoDTO.setCamBienId(khoiCamBienEntity.getCamBienId());
        camBienWithVideoDTO.setTenBaiCamBien(khoiCamBienEntity.getTenCamBien());

        return camBienWithVideoDTO;
    }
}
