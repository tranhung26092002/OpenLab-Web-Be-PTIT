package com.example.webtoeic.controller.admin;

import com.example.webtoeic.DTO.BaiGrammarWithVideoDTO;
import com.example.webtoeic.DTO.GrammarWithVideoDTO;
import com.example.webtoeic.DTO.VideoBaiGrammarResponseDTO;
import com.example.webtoeic.entity.BaiGrammar;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.repository.BaiGrammarRepository;
import com.example.webtoeic.service.BaiGrammarService;
import com.example.webtoeic.service.VideoBaiGrammarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/baigrammar")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class VideoGrammarControllerAdmin {
    @Autowired
    private VideoBaiGrammarService videoBaiGrammarService;

    @Autowired
    private BaiGrammarService baiGrammarService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("baiGrammarId") int baiGrammarId ) {

        BaseResponse response = videoBaiGrammarService.createVideo(file, title, description, baiGrammarId);


        if (response.getStatus() == 200) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update/{videoId}/{baiGrammarId}")
    public ResponseEntity<BaseResponse> updateVideo(
            @RequestParam(value = "file",required = false) MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @PathVariable("videoId") int videoId,
            @PathVariable("baiGrammarId") int baiGrammarId
    ){
        BaseResponse response = videoBaiGrammarService.updateVideo(file, title, description, videoId, baiGrammarId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete/{videoId}/{baiGrammarId}")
    public ResponseEntity<BaseResponse> deleteVideo(
        @PathVariable("videoId") int videoId,
        @PathVariable("baiGrammarId") int baiGrammarId
    ){
        BaseResponse response = videoBaiGrammarService.deleteVideo(videoId, baiGrammarId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/allWithVideos")
    public ResponseEntity<BaseResponse> getAllBaiGrammarWithVideos(){
        try {
            BaseResponse response = baiGrammarService.getAllBaiGrammar();
            if(response.getStatus() != 200){
                throw new RuntimeException("Failed to fetch BaiGrammar");
            }
            List<BaiGrammar> allBaiGrammar = (List<BaiGrammar>) response.getData();

            List<BaiGrammarWithVideoDTO> result = new ArrayList<>();
            for (BaiGrammar baiGrammar : allBaiGrammar) {
                GrammarWithVideoDTO dto = convertToDTO(baiGrammar);
                List<VideoBaiGrammarResponseDTO> videos = videoBaiGrammarService.findVideoByBaiGrammarId(baiGrammar.getBaiGrammarId());

                BaiGrammarWithVideoDTO baiGrammarWithVideoDTO = new BaiGrammarWithVideoDTO();
                baiGrammarWithVideoDTO.setGrammarWithVideoDTO(dto);
                baiGrammarWithVideoDTO.setVideos(videos);
                result.add(baiGrammarWithVideoDTO);
            }

            return ResponseEntity.ok(new BaseResponse(200, "Success", result));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(new BaseResponse(500, "Error fetching data", null));
        }
    }

    private GrammarWithVideoDTO convertToDTO(BaiGrammar baiGrammar){
        GrammarWithVideoDTO grammarWithVideoDTO = new GrammarWithVideoDTO();
        grammarWithVideoDTO.setBaiGrammarId(baiGrammar.getBaiGrammarId());
        grammarWithVideoDTO.setTenBaiGrammar(baiGrammar.getTenBaiGrammar());

        return grammarWithVideoDTO;
    }
}
