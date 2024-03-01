package com.CRUD.PROJECT.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.CRUD.PROJECT.Repo.FactureRepo;
import com.CRUD.PROJECT.entities.Facture;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;

import java.io.ByteArrayOutputStream;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;
	@Autowired
    private FactureRepo factureRepo;
    /**
     * @param htmlContent
     * @return URL du PDF
     * @throws IOException
     * @throws DocumentException 
     */

     private String generateUniqueFileName(){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHssSS");
    String timestamp= dateFormat.format(new Date());
    return "pdf_" + timestamp + ".pdf";
     }   
    public String generateAndUploadPdf(String htmlContent , String factureId) throws IOException, DocumentException {
        // Générer le PDF à partir du contenu HTML
        System.out.println(htmlContent);
        System.out.println(factureId);
       
        byte[] pdfBytes = generatePdf(htmlContent);
    
        // Uploader le PDF sur Cloudinary avec le type MIME correct (application/pdf)
        Map<String, Object> params = new HashMap<>();
        params.put("resource_type", "raw");
        String uniqueFileName= generateUniqueFileName();
        params.put("public_id",uniqueFileName);
        
 
        // Utiliser "eager" pour spécifier les transformations à effectuer côté Cloudinary
        List<Transformation> eagerTransformations = new ArrayList<>();
        Transformation eagerTransform = new Transformation().width(100).height(100).crop("pad");
        eagerTransformations.add(eagerTransform);
    
        params.put("eager", eagerTransformations);
    
        Map<String, Object> cloudinaryResponse = cloudinary.uploader().upload(pdfBytes, params);
    
        // Utiliser "https" dans l'URL du PDF
        String pdfUrl = (String) cloudinaryResponse.get("secure_url");
       System.out.println(pdfUrl);
       Optional<Facture> findFact =factureRepo.findById(factureId);
       if (findFact.isPresent()) {
        Facture facture = findFact.get();
        System.out.println("Facture trouvée : " + facture);
    
        facture.setPathUrl(pdfUrl);
        facture.setStatusSend(true);
    
        factureRepo.save(facture);
    
        System.out.println("Facture mise à jour avec succès !");
    } else {
        System.out.println("Aucune facture trouvée avec l'ID : " + factureId);
        // Gérer le cas où aucune facture n'est trouvée avec l'ID spécifié
    }
        // Retourner directement l'URL du PDF
        return pdfUrl;
    }
    
    private byte[] generatePdf(String jsonContent) throws IOException, DocumentException {
      
        String htmlContent = extractHtmlFromJson(jsonContent);

        System.out.println("HTML Content received: " + htmlContent);
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
    
            // Ajouter le CSS directement dans le document HTML
            String htmlWithCSS = "<html><head><style>"
            + "/* Ajoutez vos styles CSS directement ici */"
            + "body { font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px; margin: 10px; }"
            + "h1 { color: red; }"
            +"table{ width:100% }"
            +"tbody {width:100%}"
            +"tr{ width:100% }"
            +"td {display:flex ; justify-content:between ;}"
            + "p { color: #666; }"
            + "/* Ajoutez d'autres styles au besoin */"
            + "</style></head><body>" + htmlContent + "</body></html>";

    
            renderer.setDocumentFromString(htmlWithCSS);
    
            // Décommentez cette ligne si vous avez des styles externes dans votre HTML
            // renderer.getSharedContext().setUserAgentCallback(new NaiveUserAgent(renderer.getOutputDevice()));
    
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
    
    
    private String extractHtmlFromJson(String jsonContent) throws IOException {
        // Vérifier si le contenu est une chaîne JSON
        if (jsonContent.trim().startsWith("{")) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonContent);
            return rootNode.path("htmlContent").asText();
        } else {
            // Si c'est directement une chaîne HTML, la retourner telle quelle
            return jsonContent;
        }
    }
    
 
        
}
