package com.demo.service.PDF;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DocumentPDFService {

    public ResponseEntity<InputStreamResource> docToPDF(ByteArrayOutputStream byteArrayOutputStream,String name) throws IOException {
        // instances because of pdf
        InputStream newInputStream= new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        XWPFDocument newDoc=new XWPFDocument(newInputStream);

        PdfOptions options = PdfOptions.create();
        ByteArrayOutputStream pdfOut=new ByteArrayOutputStream();
        PdfConverter.getInstance().convert(newDoc, pdfOut, options);

        ByteArrayInputStream pdfByteArrayInputStream = new ByteArrayInputStream(pdfOut.toByteArray());
        InputStreamResource pdfDocument= new InputStreamResource(pdfByteArrayInputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=LCC-SOC-"+name+".pdf");
        MediaType word = MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(word)
                .body(pdfDocument);
    }
}
