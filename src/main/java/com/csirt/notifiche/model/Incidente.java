package com.csirt.notifiche.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidenti")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Campi Pre-Notifica (24h) ---
    private String denominazioneEnte;
    private LocalDateTime dataRilevamento;
    private String descrizioneIniziale;
    
    // Tassonomia ACN
    private String categoriaAcn; // es. Malware, DDoS, Data Breach
    private String statoQualificazione; // es. Sospetto, Confermato

    // --- Campi Notifica Completa (72h) ---
    private String impattoSuiServizi;
    private String indicatoriDiCompromissione; 

    // --- Campi Relazione Finale (1 mese) ---
    private String rootCauseAnalysis;
    private String azioniMitigazione;

    // Tracker della fase attuale (PRE_NOTIFICA, NOTIFICA_COMPLETA, RELAZIONE_FINALE)
    private String faseAttuale;

    // --- Costruttore Vuoto (Richiesto da JPA) ---
    public Incidente() {
    }

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDenominazioneEnte() { return denominazioneEnte; }
    public void setDenominazioneEnte(String denominazioneEnte) { this.denominazioneEnte = denominazioneEnte; }

    public LocalDateTime getDataRilevamento() { return dataRilevamento; }
    public void setDataRilevamento(LocalDateTime dataRilevamento) { this.dataRilevamento = dataRilevamento; }

    public String getDescrizioneIniziale() { return descrizioneIniziale; }
    public void setDescrizioneIniziale(String descrizioneIniziale) { this.descrizioneIniziale = descrizioneIniziale; }

    public String getCategoriaAcn() { return categoriaAcn; }
    public void setCategoriaAcn(String categoriaAcn) { this.categoriaAcn = categoriaAcn; }

    public String getStatoQualificazione() { return statoQualificazione; }
    public void setStatoQualificazione(String statoQualificazione) { this.statoQualificazione = statoQualificazione; }

    public String getImpattoSuiServizi() { return impattoSuiServizi; }
    public void setImpattoSuiServizi(String impattoSuiServizi) { this.impattoSuiServizi = impattoSuiServizi; }

    public String getIndicatoriDiCompromissione() { return indicatoriDiCompromissione; }
    public void setIndicatoriDiCompromissione(String indicatoriDiCompromissione) { this.indicatoriDiCompromissione = indicatoriDiCompromissione; }

    public String getRootCauseAnalysis() { return rootCauseAnalysis; }
    public void setRootCauseAnalysis(String rootCauseAnalysis) { this.rootCauseAnalysis = rootCauseAnalysis; }

    public String getAzioniMitigazione() { return azioniMitigazione; }
    public void setAzioniMitigazione(String azioniMitigazione) { this.azioniMitigazione = azioniMitigazione; }

    public String getFaseAttuale() { return faseAttuale; }
    public void setFaseAttuale(String faseAttuale) { this.faseAttuale = faseAttuale; }
}