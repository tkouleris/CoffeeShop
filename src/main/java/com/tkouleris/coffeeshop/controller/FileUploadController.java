package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.service.UploadFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequestMapping("/api/upload")
public class FileUploadController {

    private final UploadFileService uploadFileService;


    public FileUploadController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping(value="/item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> handleItemImageUpload(@RequestParam("file") MultipartFile file) throws IOException {
        uploadFileService.uploadFile(file);
        return new ResponseEntity<>("File uploaded", HttpStatus.OK);
    }

}
