package com.csirt.notifiche.service;

import com.csirt.notifiche.model.Incidente;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    public byte[] generaPdf(Incidente incidente) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            
            // Stile del titolo
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titolo = new Paragraph("Notifica Ufficiale CSIRT Italia", titleFont);
            titolo.setAlignment(Element.ALIGN_CENTER);
            titolo.setSpacingAfter(20);
            document.add(titolo);
            
            // Corpo del documento
            document.add(new Paragraph("ID Pratica: " + incidente.getId()));
            document.add(new Paragraph("Fase Attuale: " + incidente.getFaseAttuale()));
            document.add(new Paragraph("Data Rilevamento: " + incidente.getDataRilevamento()));
            document.add(new Paragraph("\n--- DETTAGLI ENTE E INCIDENTE ---"));
            document.add(new Paragraph("Denominazione Ente: " + incidente.getDenominazioneEnte()));
            document.add(new Paragraph("Categoria ACN: " + incidente.getCategoriaAcn()));
            document.add(new Paragraph("Descrizione Iniziale: " + incidente.getDescrizioneIniziale()));
            
            // Se esiste la Fase 2, aggiungiamo i dettagli
            if (incidente.getIndicatoriDiCompromissione() != null) {
                document.add(new Paragraph("\n--- INTEGRAZIONE NOTIFICA COMPLETA (72h) ---"));
                document.add(new Paragraph("Impatto sui Servizi: " + incidente.getImpattoSuiServizi()));
                document.add(new Paragraph("Indicatori di Compromissione (IoC): " + incidente.getIndicatoriDiCompromissione()));
            }
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return baos.toByteArray();
    }
}