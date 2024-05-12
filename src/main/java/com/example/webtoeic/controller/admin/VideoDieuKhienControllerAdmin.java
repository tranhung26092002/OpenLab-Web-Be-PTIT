package com.example.webtoeic.controller.admin;

import com.example.webtoeic.DTO.BaiDieuKhienWithVideoDTO;
import com.example.webtoeic.DTO.DieuKhienWithVideoDTO;
import com.example.webtoeic.DTO.VideoBaiDieuKhienResponseDTO;
import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.KhoiDieuKhienService;
import com.example.webtoeic.service.VideoBaiDieuKhienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/baidieukhien")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class VideoDieuKhienControllerAdmin {
    @Autowired
    private VideoBaiDieuKhienService videoBaiDieuKhienService;

    @Autowired
    private KhoiDieuKhienService khoiDieuKhienService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("baiDieuKhienId") int baiDieuKhienId ) {

        BaseResponse response = videoBaiDieuKhienService.createVideo(file, title, description, baiDieuKhienId);


        if (response.getStatus() == 200) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update/{videoId}/{baiDieuKhienId}")
    public ResponseEntity<BaseResponse> updateVideo(
            @RequestParam(value = "file",required = false) MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @PathVariable("videoId") int videoId,
            @PathVariable("baiDieuKhienId") int baiDieuKhienId
    ){
        BaseResponse response = videoBaiDieuKhienService.updateVideo(file, title, description, videoId, baiDieuKhienId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete/{videoId}/{baiDieuKhienId}")
    public ResponseEntity<BaseResponse> deleteVideo(
            @PathVariable("videoId") int videoId,
            @PathVariable("baiDieuKhienId") int baiDieuKhienId
    ){
        BaseResponse response = videoBaiDieuKhienService.deleteVideo(videoId, baiDieuKhienId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/allWithVideos")
    public ResponseEntity<BaseResponse> getAllBaiDieuKhienWithVideos(){
        try {
            BaseResponse response = khoiDieuKhienService.getAllKhoiDieuKhien();
            if(response.getStatus() != 200){
                throw new RuntimeException("Failed to fetch BaiGrammar");
            }
            List<KhoiDieuKhienEntity> allBaiDieuKhien = (List<KhoiDieuKhienEntity>) response.getData();

            List<BaiDieuKhienWithVideoDTO> result = new ArrayList<>();
            for (KhoiDieuKhienEntity khoiDieuKhienEntity : allBaiDieuKhien) {
                DieuKhienWithVideoDTO dto = convertToDTO(khoiDieuKhienEntity);
                List<VideoBaiDieuKhienResponseDTO> videos = videoBaiDieuKhienService.findVideoByBaiDieuKhienId(khoiDieuKhienEntity.getDieukhienId());

                BaiDieuKhienWithVideoDTO baiDieuKhienWithVideoDTO = new BaiDieuKhienWithVideoDTO();
                baiDieuKhienWithVideoDTO.setDieuKhienWithVideoDTO(dto);
                baiDieuKhienWithVideoDTO.setVideos(videos);
                result.add(baiDieuKhienWithVideoDTO);
            }

            return ResponseEntity.ok(new BaseResponse(200, "Success", result));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new BaseResponse(500, "Error fetching data", null));
        }
    }

    private DieuKhienWithVideoDTO convertToDTO(KhoiDieuKhienEntity khoiDieuKhienEntity){
        DieuKhienWithVideoDTO dieuKhienWithVideoDTO = new DieuKhienWithVideoDTO();
        dieuKhienWithVideoDTO.setDieuKhienId(khoiDieuKhienEntity.getDieukhienId());
        dieuKhienWithVideoDTO.setTenBaiDieuKhien(khoiDieuKhienEntity.getTenDieuKhien());

        return dieuKhienWithVideoDTO;
    }
}
