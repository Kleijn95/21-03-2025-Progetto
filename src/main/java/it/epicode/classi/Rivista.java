package it.epicode.classi;

import it.epicode.enums.Periodicita;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class Rivista extends CatalogoBibliotecario {
    private static final Logger logger = LoggerFactory.getLogger(Rivista.class);

    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);

        this.periodicita = periodicita;
        logger.info("Rivista creata: ISBN={}, Titolo={}, Periodicità={}", isbn, titolo, periodicita);

    }

    @Override
    public String toString() {
        return "Rivista con ISBN=" + getIsbn() + ", Titolo=" + getTitolo() + ", Anno di Pubblicazione=" + getAnnoPubblicazione()
                + ", Numero di Pagine=" + getNumeroPagine() + ", Periodicità=" + periodicita ;
    }

}