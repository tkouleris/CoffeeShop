package com.tkouleris.coffeeshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/api/upload")
public class FileUploadController {


    @PostMapping(value="/item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> handleItemImageUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("/MyFiles/Workspace/Projects/Java/coffeeshop/uploads/" + file.getOriginalFilename());
        boolean newFile = convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File uploaded", HttpStatus.OK);
    }

}
