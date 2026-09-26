package com.csirt.notifiche.controller;

import com.csirt.notifiche.model.Incidente;
import com.csirt.notifiche.repository.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.csirt.notifiche.service.PdfGeneratorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidenti")
@CrossOrigin(origins = "*") // Autorizza le chiamate dal front-end locale
public class IncidenteController {

    @Autowired
    private IncidenteRepository repository;
    @Autowired
    private PdfGeneratorService pdfService;

    @PostMapping("/prenotifica")
    public Incidente creaPreNotifica(@RequestBody Incidente incidente) {
        incidente.setFaseAttuale("PRE_NOTIFICA");
        incidente.setDataRilevamento(LocalDateTime.now());
        return repository.save(incidente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incidente> aggiornaNotifica(@PathVariable Long id, @RequestBody Incidente datiAggiornati) {
        Optional<Incidente> incidenteEsistente = repository.findById(id);
        
        if(incidenteEsistente.isPresent()) {
            Incidente incidente = incidenteEsistente.get();
            
            // Logica di aggiornamento dinamico (es. passaggio a Notifica Completa)
            if(datiAggiornati.getCategoriaAcn() != null) {
                incidente.setCategoriaAcn(datiAggiornati.getCategoriaAcn());
            }
            if(datiAggiornati.getImpattoSuiServizi() != null) {
                incidente.setImpattoSuiServizi(datiAggiornati.getImpattoSuiServizi());
            }
            if(datiAggiornati.getIndicatoriDiCompromissione() != null) {
                incidente.setIndicatoriDiCompromissione(datiAggiornati.getIndicatoriDiCompromissione());
            }
            if(datiAggiornati.getFaseAttuale() != null) {
                incidente.setFaseAttuale(datiAggiornati.getFaseAttuale());
            }
            
            return ResponseEntity.ok(repository.save(incidente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Incidente> getTuttiGliIncidenti() {
        return repository.findAll();
    }
@GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> scaricaPdf(@PathVariable Long id) {
        Optional<Incidente> incidenteOpt = repository.findById(id);
        
        if (incidenteOpt.isPresent()) {
            byte[] pdfBytes = pdfService.generaPdf(incidenteOpt.get());
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // Forza il download del file
            headers.setContentDispositionFormData("attachment", "Notifica_CSIRT_" + id + ".pdf");
            
            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        }
        return ResponseEntity.notFound().build();
    }
}