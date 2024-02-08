package com.CRUD.PROJECT.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;

import java.io.ByteArrayOutputStream;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    /**
     * @param htmlContent
     * @return URL du PDF
     * @throws IOException
     */
    public String generateAndUploadPdf(String htmlContent) throws IOException {
        // Générer le PDF à partir du contenu HTML
        System.out.println(htmlContent);
        byte[] pdfBytes = generatePdf(htmlContent);
    
        // Uploader le PDF sur Cloudinary avec le type MIME correct (application/pdf)
        Map<String, Object> params = new HashMap<>();
        params.put("resource_type", "raw");
        params.put("public_id", "nom_unique_pour_le_fichier.pdf");
       

        
        // Utiliser "eager" pour spécifier les transformations à effectuer côté Cloudinary
        List<Transformation> eagerTransformations = new ArrayList<>();
        Transformation eagerTransform = new Transformation().width(100).height(100).crop("pad");
        eagerTransformations.add(eagerTransform);
    
        params.put("eager", eagerTransformations);
    
        Map<String, Object> cloudinaryResponse = cloudinary.uploader().upload(pdfBytes, params);
    
        // Utiliser "https" dans l'URL du PDF
        String pdfUrl = (String) cloudinaryResponse.get("secure_url");
       System.out.println(pdfUrl);
        // Retourner directement l'URL du PDF
        return pdfUrl;
    }
    
    private byte[] generatePdf(String htmlContent) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
    
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA_BOLD, 12);
    
                // Décalage vers la droite (par exemple, 50 unités)
                contentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 50);
                contentStream.showText(htmlContent);
                contentStream.endText();
            }
    
            document.save(byteArrayOutputStream);
            document.close();
    
            return byteArrayOutputStream.toByteArray();
        }
    }
    
    
    
    
        
}
