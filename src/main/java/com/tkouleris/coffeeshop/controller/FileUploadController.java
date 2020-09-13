package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.exception.item.ItemNotFoundException;
import com.tkouleris.coffeeshop.facade.FileUploader;
import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.service.ItemService;
import com.tkouleris.coffeeshop.service.UploadFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/api/upload")
public class FileUploadController {

    private final FileUploader fileUploader;

    public FileUploadController(FileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }

    @PostMapping(value = "/item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> handleItemImageUpload(@RequestParam("file") MultipartFile file,
                                                        @RequestParam("item_id") long item_id) throws Exception {
        fileUploader.ItemImageUpload(file,item_id);
        return new ResponseEntity<>("File uploaded", HttpStatus.OK);
    }

}
