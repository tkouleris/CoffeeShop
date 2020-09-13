package com.tkouleris.coffeeshop.facade;

import com.tkouleris.coffeeshop.exception.item.ItemNotFoundException;
import com.tkouleris.coffeeshop.model.Item;
import com.tkouleris.coffeeshop.service.ItemService;
import com.tkouleris.coffeeshop.service.UploadFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploader {

    private final ItemService itemService;
    private final UploadFileService uploadFileService;

    public FileUploader(ItemService itemService, UploadFileService uploadFileService){
        this.itemService = itemService;
        this.uploadFileService = uploadFileService;
    }

    public void ItemImageUpload(MultipartFile file, long item_id) throws Exception {
        Item item = itemService.getitem(item_id);
        if (item == null) {
            throw new ItemNotFoundException("Item not found!");
        }
        uploadFileService.uploadFile(file);
        item.setImage_name(file.getOriginalFilename());
        itemService.updateItem(item);
    }
}
