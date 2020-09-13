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
        File convertFile = new File(getUploadFolderAbsolutePath() + file.getOriginalFilename());
        if(!convertFile.createNewFile())
        {
            throw new IOException("Cannot create File");
        }
        saveFile(file, convertFile);
    }

    private void saveFile(MultipartFile file, File convertFile) throws IOException {
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
    }

    private String getUploadFolderAbsolutePath() {
        ClassLoader classLoader = getClass().getClassLoader();
        File nullFile = new File(Objects.requireNonNull(classLoader.getResource("")).getFile());
        return nullFile.getAbsolutePath() + "/static/upload/";
    }
}
