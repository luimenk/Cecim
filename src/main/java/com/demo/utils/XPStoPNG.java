package com.demo.utils;

import com.aspose.xps.LoadOptions;
import com.aspose.xps.XpsDocument;
import com.aspose.xps.XpsLoadOptions;
import com.aspose.xps.rendering.SmoothingMode;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XPStoPNG {

    public void convertir(MultipartFile multipartFile, String folder){
        try {
            XpsDocument xpsDocument = new XpsDocument(multipartFile.getInputStream(), new XpsLoadOptions());
            com.aspose.xps.rendering.PngSaveOptions options = new com.aspose.xps.rendering.PngSaveOptions();
            options.setSmoothingMode(SmoothingMode.HighQuality);
            options.setResolution(300);
            options.setPageNumbers(new int[] {1, 2, 6});

            com.aspose.xps.rendering.ImageDevice device = new com.aspose.xps.rendering.ImageDevice();
            xpsDocument.save(device, options);

            for (int i = 0; i < device.getResult().length; i++) {
                // Iterate through partition pages
                for (int j = 0; j < device.getResult()[i].length; j++) {
                    // Initialize image output stream
                    FileOutputStream imageStream = new FileOutputStream(folder);
                    // Write image
                    imageStream.write(device.getResult()[i][j], 0, device.getResult()[i][j].length);
                    // Close the Stream
                    imageStream.close();
                }
            }

        } catch (FileNotFoundException e){
            System.out.println("filenotfound");
        } catch (IOException e){
            System.out.println("ioexception");
        }
    }
}
