package com.demo.service.QR;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.EnumMap;
import java.util.Map;

import com.demo.utils.Constantes;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.imageio.ImageIO;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.utils.EstructuraNombres;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.hibernate.engine.jdbc.internal.BinaryStreamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QRService {

    @Autowired
    MetodoMuestraService metodoMuestraService;

    public String generate(Long id, String ruta) {
        EstructuraNombres estructuraNombres = new EstructuraNombres();
        String filePath = "";

        if ("Linux".equals(System.getProperty("os.name"))){
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/" + estructuraNombres.getNombreQR() + ".png";
        } else {
            filePath = System.getProperty("user.home") + "/" + estructuraNombres.getNombreQR() + ".png";
        }

        String myCodeText = id.toString();
        String fileName = estructuraNombres.getNombreQR()+".png";
        int size = 250;
        String fileType = "png";
        File myFile = new File(filePath);

        try {
            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */

            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            conexionFTP(fileName, ruta, convertirQRaInputStream(image));

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nSe creó satisfactoriamente el QR.");

        return fileName;
    }

    public String generateToLab(Long id, String ruta) {
        EstructuraNombres estructuraNombres = new EstructuraNombres();
        String filePath = "";

        if ("Linux".equals(System.getProperty("os.name"))){
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/" + estructuraNombres.getNombreQR() + ".png";
        } else {
            filePath = System.getProperty("user.home") + "/" + estructuraNombres.getNombreQR() + ".png";
        }

        MetodoMuestra metodoMuestra = metodoMuestraService.findById(id);

        String myCodeText = metodoMuestra.getMethod().getRuta()+"/"+metodoMuestra.getMetodoMuestraId();
        String fileName = estructuraNombres.getNombreQR()+".png";

        int size = 250;
        String fileType = "png";
        File myFile = new File(filePath);

        try {
            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */

            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            conexionFTP(fileName, ruta, convertirQRaInputStream(image));

            //ImageIO.write(image, fileType, myFile);

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nSe creó satisfactoriamente el QR.");

        return fileName;
    }

    public InputStream convertirQRaInputStream(BufferedImage imagen) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(imagen, "png", byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public void conexionFTP(String fileName, String ruta, InputStream archivo) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(Constantes.SERVER, 21);
            ftpClient.login(Constantes.USER, Constantes.PASSWORD);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            boolean done = ftpClient.storeFile(ruta + fileName, archivo);

            if (done) {
                System.out.println("se cargó exitosamente");
            }
        } catch (IOException ex){
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()){
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

//        if (ftpClient.login(Constantes.USER, Constantes.PASSWORD)){
//            ftpClient.enterLocalPassiveMode(); /// IMPORTANTE
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            boolean result = ftpClient.storeFile(ruta + fileName, archivo);
//            if (!result){
//                System.out.println("no lo guardó");
//            }
//            ftpClient.logout();
//            ftpClient.disconnect();
//        } else {
//            System.out.println("No se pudo conectar al FTP");
//        }
    }
}
