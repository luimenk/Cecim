package com.demo.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveInServer {
    public String SaveInServer(MultipartFile multipartFile, String folder) throws IOException {
        //ImageIO
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String strinDate=dateFormat.format(date);
        String fileName=date.getTime()+"."+extension;

        //System.getProperty("os.name");
        folder=folder+fileName;
        byte[] bytes=multipartFile.getBytes();

        Path path= Paths.get(folder);

        Files.write(path,bytes);

        return fileName;
    }
}
