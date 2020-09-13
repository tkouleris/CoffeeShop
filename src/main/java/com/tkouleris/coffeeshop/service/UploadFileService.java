package com.tkouleris.coffeeshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class UploadFileService {

    public void uploadFile(MultipartFile file) throws IOException {
        File uploadedFile = new File(getUploadFolderAbsolutePath() + file.getOriginalFilename());
        boolean success = uploadedFile.createNewFile();
        saveFile(file, uploadedFile);
    }

    private String getUploadFolderAbsolutePath() {
        ClassLoader classLoader = getClass().getClassLoader();
        File nullFile = new File(Objects.requireNonNull(classLoader.getResource("")).getFile());
        return nullFile.getAbsolutePath() + "/static/upload/";
    }

    private void saveFile(MultipartFile file, File uploadedFile) throws IOException {
        FileOutputStream fout = new FileOutputStream(uploadedFile);
        fout.write(file.getBytes());
        fout.close();
    }
}
