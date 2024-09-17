package com.CRUD.PROJECT.Controller;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.PROJECT.PdfGenerationRequest;
import com.CRUD.PROJECT.Service.CloudinaryService;
import com.lowagie.text.DocumentException;

@RestController
@CrossOrigin("*,https://navitrack-dashbord.onrender.com/")
@RequestMapping("/api")
public class PdfController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/generate-and-upload-pdf")
    public String generateAndUploadPdf(@RequestBody PdfGenerationRequest  request) throws IOException, DocumentException {
        String htmlContent = request.getHtmlContent();
        String factureId = request.getFactureId();
        return cloudinaryService.generateAndUploadPdf(htmlContent,factureId);
    }
}
