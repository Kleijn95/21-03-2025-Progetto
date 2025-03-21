package it.epicode.classi;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter

public class Libro extends CatalogoBibliotecario {
    private static final Logger logger = LoggerFactory.getLogger(Libro.class);

    private String autore;
    private String genere;

    public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
        logger.info("Libro creato: ISBN={}, Titolo={}, Autore={}, Genere={}", isbn, titolo, autore, genere);
    }
    @Override
    public String toString() {
        return "Libro con ISBN=" + getIsbn() + ", Titolo=" + getTitolo() + ", Anno di Pubblicazione=" + getAnnoPubblicazione()
                + ", Numero di Pagine=" + getNumeroPagine() + ", Autore=" + autore + ", Genere=" + genere;
    }


}


