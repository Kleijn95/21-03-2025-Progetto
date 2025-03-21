package it.epicode.classi;

import it.epicode.exceptions.ElementoNonTrovatoException;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import static it.epicode.enums.Periodicita.MENSILE;
import static it.epicode.enums.Periodicita.SEMESTRALE;

@Data
@ToString
public class Archivio {
    private static final Logger logger = LoggerFactory.getLogger(Archivio.class);
    private List<CatalogoBibliotecario> catalogo = new ArrayList<>();

    Libro libro1 = new Libro("12345", "Il Grande Gatsby", 1925, 200, "F. Scott Fitzgerald", "Romanzo");
    Libro libro2 = new Libro("67890", "1984", 1949, 328, "George Orwell", "Distopia");
    Rivista rivista1 = new Rivista("54321", "National Geographic", 2023, 100, SEMESTRALE);
    Rivista rivista2 = new Rivista("98765", "Time", 2023, 50, MENSILE);

    public Archivio() {
        catalogo.add(libro1);
        catalogo.add(libro2);
        catalogo.add(rivista1);
        catalogo.add(rivista2);
    }



    public void aggiungiElemento(CatalogoBibliotecario elemento) throws ElementoNonTrovatoException {
        if (catalogo.stream().anyMatch(e -> e.getIsbn().equals(elemento.getIsbn()))) {
            logger.error("Elemento già presente nel catalogo con ISBN: {}", elemento.getIsbn());
            throw new ElementoNonTrovatoException("Elemento già presente nel catalogo con ISBN: " + elemento.getIsbn());
        } else {
            catalogo.add(elemento);
            logger.info("Elemento aggiunto con successo: {}", elemento);
        }
    }

    public void rimuoviElemento(String isbn) throws ElementoNonTrovatoException {
        CatalogoBibliotecario elementoTrovato = catalogo.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ElementoNonTrovatoException("Elemento non presente nel catalogo, impossibile eliminare con ISBN: " + isbn));

        catalogo.remove(elementoTrovato);
        logger.info("Elemento con ISBN {} è stato rimosso correttamente.", isbn);
    }

    public CatalogoBibliotecario ricercaPerIsbn(String isbn) throws ElementoNonTrovatoException {
        CatalogoBibliotecario elementoTrovato = catalogo.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> {
                    logger.error("Elemento non trovato con ISBN: {}", isbn);
                    return new ElementoNonTrovatoException("Elemento non trovato con ISBN: " + isbn);
                });

        logger.info("Elemento trovato: {}", elementoTrovato);
        return elementoTrovato;
    }

    public void ricercaPerAutore(String autore) throws ElementoNonTrovatoException {
        List<CatalogoBibliotecario> elementiTrovati = catalogo.stream()
                .filter(e -> e instanceof Libro && ((Libro) e).getAutore().equals(autore))
                .collect(Collectors.toList());

        if (!elementiTrovati.isEmpty()) {
            logger.info("Elementi trovati per autore {}:", autore);
            elementiTrovati.forEach(elemento -> logger.info("Elemento trovato: {}", elemento));
        } else {
            logger.error("Nessun elemento trovato per l'autore: {}", autore);
            throw new ElementoNonTrovatoException("Nessun elemento trovato per l'autore: " + autore);
        }
    }

    public void ricercaPerAnnoPubblicazione(int annoPubblicazione) throws ElementoNonTrovatoException {
        List<CatalogoBibliotecario> elementiTrovati = catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == annoPubblicazione)
                .collect(Collectors.toList());

        if (!elementiTrovati.isEmpty()) {
            logger.info("Elementi pubblicati nell'anno {}:", annoPubblicazione);
            elementiTrovati.forEach(elemento -> logger.info("Elemento trovato: {}", elemento));
        } else {
            logger.error("Nessun elemento trovato per l'anno di pubblicazione: {}", annoPubblicazione);
            throw new ElementoNonTrovatoException("Nessun elemento trovato per l'anno di pubblicazione: " + annoPubblicazione);
        }
    }

    public void modificaElemento(String isbn, CatalogoBibliotecario nuovoElemento) throws ElementoNonTrovatoException {
        for (int i = 0; i < catalogo.size(); i++) {
            CatalogoBibliotecario elemento = catalogo.get(i);
            if (elemento.getIsbn().equals(isbn)) {
                if (elemento instanceof Libro && nuovoElemento instanceof Libro) {
                    catalogo.set(i, nuovoElemento);
                    logger.info("Elemento Libro con ISBN {} modificato correttamente.", isbn);
                    return;
                } else if (elemento instanceof Rivista && nuovoElemento instanceof Rivista) {
                    catalogo.set(i, nuovoElemento);
                    logger.info("Elemento Rivista con ISBN {} modificato correttamente.", isbn);
                    return;
                } else {
                    logger.error("Tipo di elemento non compatibile per la modifica con ISBN: {}", isbn);
                    throw new ElementoNonTrovatoException("Tipo di elemento non compatibile per la modifica con ISBN: " + isbn);
                }
            }
        }
        logger.error("Elemento non trovato con ISBN: {}", isbn);
        throw new ElementoNonTrovatoException("Elemento non trovato con ISBN: " + isbn);
    }

    public void stampaStatisticheCatalogo() {
        long numeroLibri = catalogo.stream().filter(e -> e instanceof Libro).count();
        long numeroRiviste = catalogo.stream().filter(e -> e instanceof Rivista).count();
        CatalogoBibliotecario maxPagine = catalogo.stream().max((e1, e2) -> Integer.compare(e1.getNumeroPagine(), e2.getNumeroPagine())).orElse(null);
        OptionalDouble mediaPagine = catalogo.stream().mapToInt(CatalogoBibliotecario::getNumeroPagine).average();

        logger.info("Statistiche Catalogo: ");
        logger.info("Numero di libri: {}", numeroLibri);
        logger.info("Numero di riviste: {}", numeroRiviste);
        logger.info("Elemento con il maggior numero di pagine: {}", maxPagine);
        logger.info("Media pagine: {}", mediaPagine.orElse(0.0));
    }

    public int size() {
        return catalogo.size();
    }

    public void stampaCatalogo() {
        catalogo.forEach(elemento -> logger.info("Elemento: {}", elemento));
    }
}




