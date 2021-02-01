package com.demo.utils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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

        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String strinDate=dateFormat.format(date);


        if (extension.equals("xps")){
            String fileName=date.getTime()+".png";
            folder=folder+fileName;
            XPStoPNG xpStoPNG = new XPStoPNG();
            xpStoPNG.convertir(multipartFile, folder);
            return fileName;
        } else {
            String fileName=date.getTime()+"."+extension;
            folder=folder+fileName;
            byte[] bytes=multipartFile.getBytes();
            Path path= Paths.get(folder);
            Files.write(path,bytes);
            return fileName;
        }
    }
}
