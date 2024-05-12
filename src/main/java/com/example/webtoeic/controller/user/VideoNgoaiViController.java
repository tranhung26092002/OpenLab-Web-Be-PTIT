package com.example.webtoeic.controller.user;

import com.example.webtoeic.DTO.VideoBaiDieuKhienResponseDTO;
import com.example.webtoeic.DTO.VideoBaiNgoaiViResponseDTO;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.VideoBaiDieuKhienService;
import com.example.webtoeic.service.VideoNgoaiViService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/baingoaivi/video")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class VideoNgoaiViController {

    @Autowired
    private VideoNgoaiViService videoNgoaiViService;

    @GetMapping("/byVideoBaiNgoaiVi/{baiNgoaiViId}")
    public ResponseEntity<BaseResponse> getVideosByBaiDieuKhien(@PathVariable int baiNgoaiViId){
        try {
            List<VideoBaiNgoaiViResponseDTO> listVideoDTO = videoNgoaiViService.findVideoByBaiNgoaiViId(baiNgoaiViId);
            return ResponseEntity.ok(new BaseResponse(200, "Success", listVideoDTO));
        } catch (AccessDeniedException ade) {
            return ResponseEntity.status(403).body(new BaseResponse(403, "Access denied", null));
        } catch(Exception e){
            return ResponseEntity.status(404).body(new BaseResponse(404, "BaiNgoaiVi not found or video not found", null));
        }
    }
}
