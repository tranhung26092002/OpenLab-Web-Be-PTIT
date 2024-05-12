package com.example.webtoeic.controller.admin;

import com.example.webtoeic.DTO.*;
import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import com.example.webtoeic.entity.KhoiNgoaiViEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.KhoiDieuKhienService;
import com.example.webtoeic.service.KhoiNgoaiViService;
import com.example.webtoeic.service.VideoBaiDieuKhienService;
import com.example.webtoeic.service.VideoNgoaiViService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/baingoaivi")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class VideoNgoaiViControllerAdmin {
    @Autowired
    private VideoNgoaiViService videoNgoaiViService;

    @Autowired
    private KhoiNgoaiViService khoiNgoaiViService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("baiNgoaiViId") int baiNgoaiViId ) {
        BaseResponse response = videoNgoaiViService.createVideo(file, title, description, baiNgoaiViId);


        if (response.getStatus() == 200) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update/{videoId}/{baiNgoaiViId}")
    public ResponseEntity<BaseResponse> updateVideo(
            @RequestParam(value = "file",required = false) MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @PathVariable("videoId") int videoId,
            @PathVariable("baiNgoaiViId") int baiNgoaiViId
    ){
        BaseResponse response = videoNgoaiViService.updateVideo(file, title, description, videoId, baiNgoaiViId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete/{videoId}/{baiNgoaiViId}")
    public ResponseEntity<BaseResponse> deleteVideo(
            @PathVariable("videoId") int videoId,
            @PathVariable("baiNgoaiViId") int baiNgoaiViId
    ){
        BaseResponse response = videoNgoaiViService.deleteVideo(videoId, baiNgoaiViId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/allWithVideos")
    public ResponseEntity<BaseResponse> getAllBaiDieuKhienWithVideos(){
        try {
            BaseResponse response = khoiNgoaiViService.getAllKhoiNgoaiVi();
            if(response.getStatus() != 200){
                throw new RuntimeException("Failed to fetch BaiGrammar");
            }
            List<KhoiNgoaiViEntity> allBaiNgoaiVi = (List<KhoiNgoaiViEntity>) response.getData();

            List<BaiNgoaiViWithVideoDTO> result = new ArrayList<>();
            for (KhoiNgoaiViEntity khoiNgoaiViEntity : allBaiNgoaiVi) {
                NgoaiViWithVideoDTO dto = convertToDTO(khoiNgoaiViEntity);
                List<VideoBaiNgoaiViResponseDTO> videos = videoNgoaiViService.findVideoByBaiNgoaiViId(khoiNgoaiViEntity.getNgoaiViId());

                BaiNgoaiViWithVideoDTO baiNgoaiViWithVideoDTO = new BaiNgoaiViWithVideoDTO();
                baiNgoaiViWithVideoDTO.setNgoaiViWithVideoDTO(dto);
                baiNgoaiViWithVideoDTO.setVideos(videos);
                result.add(baiNgoaiViWithVideoDTO);
            }

            return ResponseEntity.ok(new BaseResponse(200, "Success", result));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new BaseResponse(500, "Error fetching data", null));
        }
    }

    private NgoaiViWithVideoDTO convertToDTO(KhoiNgoaiViEntity khoiNgoaiViEntity){
        NgoaiViWithVideoDTO ngoaiViWithVideoDTO = new NgoaiViWithVideoDTO();
        ngoaiViWithVideoDTO.setNgoaiViId(khoiNgoaiViEntity.getNgoaiViId());
        ngoaiViWithVideoDTO.setTenBaiNgoaiVi(khoiNgoaiViEntity.getTenNgoaiVi());

        return ngoaiViWithVideoDTO;
    }
}
