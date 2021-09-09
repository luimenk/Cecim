package com.demo.utils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveInServer {
    public String SaveInServer(MultipartFile multipartFile, String folder) throws IOException {

        String extension;

        if (multipartFile.getOriginalFilename().equals("blob")){
            extension = "png";
        } else {
            extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        }

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
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ImageIO.write(image, fileType, byteArrayOutputStream);
//            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            String fileName=date.getTime()+"."+extension;

            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(Constantes.SERVER);
            if (ftpClient.login(Constantes.USER, Constantes.PASSWORD)){
                ftpClient.enterLocalPassiveMode(); /// IMPORTANTE
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                boolean result = ftpClient.storeFile(folder + fileName, multipartFile.getInputStream());
                ftpClient.logout();
                ftpClient.disconnect();
            } else {
                System.out.println("No se pudo conectar al FTP");
            }
            return fileName;
//            folder=folder+fileName;
//            byte[] bytes=multipartFile.getBytes();
//            Path path= Paths.get(folder);
//            Files.write(path,bytes);
//            return fileName;
        }
    }
}
