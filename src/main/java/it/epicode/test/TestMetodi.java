package it.epicode.test;

import it.epicode.classi.Archivio;
import it.epicode.classi.CatalogoBibliotecario;
import it.epicode.classi.Libro;
import it.epicode.classi.Rivista;
import it.epicode.enums.Periodicita;
import it.epicode.exceptions.ElementoNonTrovatoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMetodi {
    private static final Logger logger = LoggerFactory.getLogger(TestMetodi.class);

    public static void main(String[] args) {
        // Creazione dell'archivio test
        Archivio archivio = new Archivio();

        // Creazione di libri e riviste per testare
        CatalogoBibliotecario libro1 = new Libro("123", "Il Signore degli Anelli", 1954, 1000, "JRR Tolkien", "Fantasy");
        CatalogoBibliotecario libro2 = new Libro("456", "Harry Potter e la Pietra Filosofale", 1997, 223, "JK Rowling", "Fantasy");
        CatalogoBibliotecario rivista1 = new Rivista("789", "National Geographic", 1888, 100, Periodicita.MENSILE);
        CatalogoBibliotecario rivista2 = new Rivista("012", "Time", 1923, 50, Periodicita.SETTIMANALE);

        // Test aggiunta elementi e aggiunta elemento già presente
        aggiungiElementi(archivio, libro1, libro2, rivista1, rivista2);
        aggiungiElementi(archivio, libro1);

        logger.info("------------------------");
        // Test di ricerca per ISBN presente e non presente
        testRicercaPerIsbn(archivio, "123");
        testRicercaPerIsbn(archivio, "999");

        // Test di ricerca per anno di pubblicazione presente e non
        logger.info("------------------------");
        testRicercaPerAnnoPubblicazione(archivio, 1997);
        testRicercaPerAnnoPubblicazione(archivio, 2000);

        // Test di ricerca per autore presente e non
        logger.info("------------------------");
        testRicercaPerAutore(archivio, "JK Rowling");
        testRicercaPerAutore(archivio, "Meridjan");

        // Test di rimozione dell'elemento presente e non
        logger.info("------------------------");
        testRimuoviElemento(archivio, "123");
        testRimuoviElemento(archivio, "999");

        // Test di modifica di un elemento
        logger.info("------------------------");
        testModificaElemento(archivio, "456", new Libro("456", "Harry Potter e la Camera dei Segreti", 1998, 251, "JK Rowling", "Fantasy"));

        // Test delle statistiche del catalogo
        logger.info("------------------------");
        testStampaStatisticheCatalogo(archivio);
    }


    public static void aggiungiElementi(Archivio archivio, CatalogoBibliotecario... elementi) {
        for (CatalogoBibliotecario elemento : elementi) {
            try {
                archivio.aggiungiElemento(elemento);
            } catch (Exception e) {
                logger.error("Errore nell'aggiungere l'elemento: " + e.getMessage());
            }
        }
        logger.info("\nCatalogo dopo l'aggiunta degli elementi:");
        archivio.getCatalogo().forEach(elemento -> logger.info(elemento.toString()));
    }


    public static void testRicercaPerIsbn(Archivio archivio, String isbn) {
        try {
            archivio.ricercaPerIsbn(isbn);
        } catch (ElementoNonTrovatoException e) {
            logger.error(e.getMessage());
        }
    }


    public static void testRicercaPerAnnoPubblicazione(Archivio archivio, int annoPubblicazione) {
        try {
            archivio.ricercaPerAnnoPubblicazione(annoPubblicazione);
        } catch (ElementoNonTrovatoException e) {
            logger.error(e.getMessage());
        }
    }


    public static void testRicercaPerAutore(Archivio archivio, String autore) {
        try {
            archivio.ricercaPerAutore(autore);
        } catch (ElementoNonTrovatoException e) {
            logger.error(e.getMessage());
        }
    }


    public static void testRimuoviElemento(Archivio archivio, String codiceISBN) {
        try {
            archivio.rimuoviElemento(codiceISBN);
            logger.info("Elemento rimosso con successo.");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("\nCatalogo dopo la rimozione dell'elemento:");
        archivio.getCatalogo().forEach(elemento -> logger.info(elemento.toString()));
    }


    public static void testModificaElemento(Archivio archivio, String isbn, CatalogoBibliotecario nuovoElemento) {
        try {
            archivio.modificaElemento(isbn, nuovoElemento);
            logger.info("Elemento modificato con successo.");
        } catch (ElementoNonTrovatoException e) {
            logger.error(e.getMessage());
        }
        logger.info("\nCatalogo dopo la modifica dell'elemento:");
        archivio.getCatalogo().forEach(elemento -> logger.info(elemento.toString()));
    }


    public static void testStampaStatisticheCatalogo(Archivio archivio) {
        logger.info("\nStatistiche del catalogo:");
        archivio.stampaStatisticheCatalogo();
    }
}


