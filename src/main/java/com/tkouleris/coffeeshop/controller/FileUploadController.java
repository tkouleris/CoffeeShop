package com.tkouleris.coffeeshop.controller;

import com.tkouleris.coffeeshop.exception.item.ItemNotFoundException;
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

    private final UploadFileService uploadFileService;
    private final ItemService itemService;

    public FileUploadController(UploadFileService uploadFileService, ItemService itemService) {
        this.uploadFileService = uploadFileService;
        this.itemService = itemService;
    }

    @PostMapping(value = "/item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> handleItemImageUpload(@RequestParam("file") MultipartFile file,
                                                        @RequestParam("item_id") long item_id) throws Exception {
        Item item = itemService.getitem(item_id);
        if (item == null) {
            throw new ItemNotFoundException("Item not found!");
        }
        uploadFileService.uploadFile(file);
        item.setImage_name(file.getOriginalFilename());
        itemService.updateItem(item);
        return new ResponseEntity<>("File uploaded", HttpStatus.OK);
    }

}
