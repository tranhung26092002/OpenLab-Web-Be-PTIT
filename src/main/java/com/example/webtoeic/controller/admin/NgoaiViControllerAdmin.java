package com.example.webtoeic.controller.admin;

import com.example.webtoeic.entity.KhoiDieuKhienEntity;
import com.example.webtoeic.entity.KhoiNgoaiViEntity;
import com.example.webtoeic.payload.response.BaseResponse;
import com.example.webtoeic.service.KhoiNgoaiViService;
import com.example.webtoeic.service.StorageService;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/IoT/ngoaivi")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class NgoaiViControllerAdmin {

    @Autowired
    private KhoiNgoaiViService khoiNgoaiViService;

    @Autowired
    private StorageService storageService;

    private String getCorrectExtension(byte[] imageData) {
        // First bytes of different image formats
        byte[] jpeg = new byte[] {(byte) 0xFF, (byte) 0xD8};
        byte[] png = new byte[] {(byte) 0x89, (byte) 0x50};

        if (imageData.length > 1) {
            if (imageData[0] == jpeg[0] && imageData[1] == jpeg[1]) {
                return "jpeg";
            } else if (imageData[0] == png[0]) {
                return "png";
            }
        }
        return "unknown";
    }



    private List<MultipartFile> extractImagesFromDoc(XWPFDocument document) {
        List<MultipartFile> imageFiles = new ArrayList<>();
        // Extract images
        for (XWPFPictureData pictureData : document.getAllPictures()) {
            byte[] bytes = pictureData.getData();
            String ext = this.getCorrectExtension(bytes);
            String imageName = new File(pictureData.getPackagePart().getPartName().getName()).getName();


            MultipartFile imageFile = new MockMultipartFile(imageName, imageName, "image/" + ext, bytes);
            imageFiles.add(imageFile);
        }
        return imageFiles;
    }

    private List<MultipartFile> extractImagesFromDocForHWPF(HWPFDocument document) throws IOException {
        List<MultipartFile> images = new ArrayList<>();

        List<Picture> pictures = document.getPicturesTable().getAllPictures();
        for (Picture picture : pictures) {
            byte[] bytes = picture.getContent();
            String ext = picture.suggestFileExtension();
            String imageFileName = picture.suggestFullFileName();

            // Use MockMultipartFile
            MultipartFile imageFile = new MockMultipartFile(imageFileName, imageFileName, "image/" + ext, bytes);
            images.add(imageFile);
        }
        return images;
    }

    @Value("${app.base-upload-image-url}")
    private String BASE_UPLOAD_IMAGE_URL;


    @PostMapping("/create")
    public ResponseEntity<?> createKhoiNgoaiVi(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tenKhoiNgoaiVi") String tenKhoiNgoaiVi,
            @RequestParam(value = "contentHTML") MultipartFile contentFile
    ) {
        String docContentAsHtml = "";
        String fileName;
        try {
            // Lưu file vào một thư mục cụ thể
            fileName = storageService.uploadImageToFileSystem(file);
            List<String> imageNames = new ArrayList<>();
            if (contentFile != null && !contentFile.isEmpty()) {
                List<MultipartFile> images = null;
                if (contentFile.getOriginalFilename().endsWith(".doc")) {
                    HWPFDocument document = new HWPFDocument(contentFile.getInputStream());

                    images = extractImagesFromDocForHWPF(document);

                    WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
                    wordToHtmlConverter.processDocument(document);
                    StringWriter stringWriter = new StringWriter();
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(new DOMSource(wordToHtmlConverter.getDocument()), new StreamResult(stringWriter));
                    docContentAsHtml = stringWriter.toString();

                    for (String imageName : imageNames) {
                        String originalName = imageName.split("_", 2)[1];
                        String oldPath = "word/media/" + originalName;
                        String newPath = BASE_UPLOAD_IMAGE_URL + imageName;
                        docContentAsHtml = docContentAsHtml.replace(oldPath, newPath);
                    }

                } else if (contentFile.getOriginalFilename().endsWith(".docx")) {
                    XWPFDocument document = new XWPFDocument(contentFile.getInputStream());
                    images = extractImagesFromDoc(document);  // Extract images first

                    XHTMLOptions options = XHTMLOptions.create().indent(4);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    XHTMLConverter.getInstance().convert(document, out, options);
                    docContentAsHtml = new String(out.toByteArray(), StandardCharsets.UTF_8);


                    for (MultipartFile image : images) {
                        String uploadedImageName = storageService.uploadImageToFileSystem(image);
                        imageNames.add(uploadedImageName);
                    }
                } else {
                    return new ResponseEntity<>("Unsupported file type", HttpStatus.BAD_REQUEST);
                }
                for (String imageName : imageNames) {
                    String originalName = imageName.split("_", 2)[1];
                    String oldPath = "word/media/" + originalName;
                    String newPath = BASE_UPLOAD_IMAGE_URL + imageName;
                    docContentAsHtml = docContentAsHtml.replace(oldPath, newPath);
                }

            }
            String joinedImageNames = String.join(",", imageNames);

            KhoiNgoaiViEntity khoiNgoaiViEntity = new KhoiNgoaiViEntity();
            khoiNgoaiViEntity.setAnhNgoaiVi(BASE_UPLOAD_IMAGE_URL + fileName);
            khoiNgoaiViEntity.setTenNgoaiVi(tenKhoiNgoaiVi);
            khoiNgoaiViEntity.setContentHtml(docContentAsHtml);
            khoiNgoaiViEntity.setContentMarkDown(joinedImageNames);

            BaseResponse response = khoiNgoaiViService.save(khoiNgoaiViEntity);
            if (response.getStatus() == 200) {
                return new ResponseEntity<>(response.getData(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Error from service: " + response.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {

            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateKhoiNgoaiVi(
            @PathVariable int id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("tenKhoiNgoaiVi") String tenKhoiNgoaiVi,
            @RequestParam(value = "contentHTML", required = false) MultipartFile contentFile
    ) {
        BaseResponse fetchedKhoiDieuKhien = khoiNgoaiViService.getKhoiNgoaiVi(id);
        if (fetchedKhoiDieuKhien.getStatus() != 200) {
            return new ResponseEntity<>(fetchedKhoiDieuKhien.getMessage(), HttpStatus.NOT_FOUND);
        }

        try {
            String fileName = storageService.uploadImageToFileSystem(file);
            String docContentAsHtml = "";

            if (contentFile != null && !contentFile.isEmpty()) {
                List<MultipartFile> images;

                if (contentFile.getOriginalFilename().endsWith(".doc")) {
                    HWPFDocument document = new HWPFDocument(contentFile.getInputStream());
                    images = extractImagesFromDocForHWPF(document);

                    WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
                    wordToHtmlConverter.processDocument(document);

                    StringWriter stringWriter = new StringWriter();
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(new DOMSource(wordToHtmlConverter.getDocument()), new StreamResult(stringWriter));
                    docContentAsHtml = stringWriter.toString();

                } else if (contentFile.getOriginalFilename().endsWith(".docx")) {
                    XWPFDocument document = new XWPFDocument(contentFile.getInputStream());
                    images = extractImagesFromDoc(document);

                    XHTMLOptions options = XHTMLOptions.create().indent(4);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    XHTMLConverter.getInstance().convert(document, out, options);
                    docContentAsHtml = new String(out.toByteArray(), StandardCharsets.UTF_8);

                } else {
                    return new ResponseEntity<>("Unsupported file type", HttpStatus.BAD_REQUEST);
                }

                for (MultipartFile image : images) {
                    String uploadedImageName = storageService.uploadImageToFileSystem(image);
                    String pathToReplace = BASE_UPLOAD_IMAGE_URL + uploadedImageName;
                    String originalName = image.getOriginalFilename();
                    docContentAsHtml = docContentAsHtml.replace("\"word/media/" + originalName + "\"", "\"" + pathToReplace + "\"");
                }
            }

            KhoiNgoaiViEntity khoiNgoaiViEntity = new KhoiNgoaiViEntity();
            khoiNgoaiViEntity.setAnhNgoaiVi(BASE_UPLOAD_IMAGE_URL + fileName);
            khoiNgoaiViEntity.setTenNgoaiVi(tenKhoiNgoaiVi);
            khoiNgoaiViEntity.setContentHtml(docContentAsHtml);


            BaseResponse updateResponse = khoiNgoaiViService.update(id, khoiNgoaiViEntity);
            return (updateResponse.getStatus() == 200)
                    ? new ResponseEntity<>(updateResponse.getData(), HttpStatus.OK)
                    : new ResponseEntity<>(updateResponse.getMessage(), HttpStatus.BAD_REQUEST);

        } catch(Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteKhoiNgoaiVi(@PathVariable int id) {
        BaseResponse deleteResponse = khoiNgoaiViService.delete(id);
        if (deleteResponse.getStatus() == 200) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(deleteResponse.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
