package com.CRUD.PROJECT;
public class PdfGenerationRequest  {
    private String htmlContent;
    private String factureId;
    public String getHtmlContent() {
        return htmlContent;
    }
    public String getFactureId() {
        return factureId;
    }
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
    public void setFactureId(String factureId) {
        this.factureId = factureId;
    }
    public PdfGenerationRequest(String htmlContent, String factureId) {
        this.htmlContent = htmlContent;
        this.factureId = factureId;
    }
    
}
