package com.example.webtoeic.controller.user;

import com.example.webtoeic.DTO.VideoBaiCamBienResponseDTO;
import com.example.webtoeic.DTO.VideoBaiGrammarResponseDTO;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.VideoBaiCamBienService;
import com.example.webtoeic.service.VideoBaiGrammarService;
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
@RequestMapping("/api/baicambien/video")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class VideoCamBienController {
    @Autowired
    private VideoBaiCamBienService videoBaiCamBienService;

    @GetMapping("/byVideoBaiCamBien/{baiCamBienId}")
    public ResponseEntity<BaseResponse> getVideosByBaiCamBien(@PathVariable int baiCamBienId){
        try {
            List<VideoBaiCamBienResponseDTO> listVideoDTO = videoBaiCamBienService.findVideoByBaiCamBienId(baiCamBienId);
            return ResponseEntity.ok(new BaseResponse(200, "Success", listVideoDTO));
        } catch (AccessDeniedException ade) {
            return ResponseEntity.status(403).body(new BaseResponse(403, "Access denied", null));
        } catch(Exception e){
            return ResponseEntity.status(404).body(new BaseResponse(404, "BaiGrammar not found or video not found", null));
        }
    }
}
