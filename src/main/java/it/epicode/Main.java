package it.epicode;

import it.epicode.classi.Archivio;
import it.epicode.classi.CatalogoBibliotecario;
import it.epicode.classi.Libro;
import it.epicode.classi.Rivista;
import it.epicode.enums.Periodicita;
import it.epicode.exceptions.ElementoNonTrovatoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Archivio archivio = new Archivio();

        while (true) {
            logger.debug("Mostra il menu principale");
            System.out.println("Numero di elementi nell'archivio: " + archivio.size());


            if (archivio.size() == 0) {
                System.out.println("L'archivio è vuoto.");
            }
            System.out.println("\n--- Menu Archivio ---");
            System.out.println("1. Aggiungi Libro");
            System.out.println("2. Aggiungi Rivista");
            System.out.println("3. Ricerca per ISBN");
            System.out.println("4. Ricerca per Autore");
            System.out.println("5. Ricerca per Anno di Pubblicazione");
            System.out.println("6. Rimuovi Elemento");
            System.out.println("7. Modifica Elemento");
            System.out.println("8. Stampa Statistiche");
            System.out.println("9. Esci");
            System.out.println("10. Stampa Catalogo");
            System.out.print("Scegli un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    logger.info("Aggiungi un libro");
                    System.out.println("\n--- Aggiungi Libro ---");
                    System.out.print("Inserisci ISBN: ");
                    String isbnLibro = scanner.nextLine();
                    System.out.print("Inserisci titolo: ");
                    String titoloLibro = scanner.nextLine();
                    System.out.print("Inserisci anno di pubblicazione: ");
                    int annoPubblicazioneLibro = scanner.nextInt();
                    System.out.print("Inserisci numero di pagine: ");
                    int numeroPagineLibro = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci autore: ");
                    String autoreLibro = scanner.nextLine();
                    System.out.print("Inserisci genere: ");
                    String genereLibro = scanner.nextLine();
                    CatalogoBibliotecario libro = new Libro(isbnLibro, titoloLibro, annoPubblicazioneLibro, numeroPagineLibro, autoreLibro, genereLibro);
                    try {
                        archivio.aggiungiElemento(libro);
                        logger.info("Libro aggiunto con successo");
                        System.out.println("Libro aggiunto con successo!\n");
                    } catch (Exception e) {
                        logger.error("Errore nell'aggiunta del libro: " + e.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    logger.info("Aggiungi una rivista");
                    System.out.println("\n--- Aggiungi Rivista ---");
                    System.out.print("Inserisci ISBN: ");
                    String isbnRivista = scanner.nextLine();
                    System.out.print("Inserisci titolo: ");
                    String titoloRivista = scanner.nextLine();
                    System.out.print("Inserisci anno di pubblicazione: ");
                    int annoPubblicazioneRivista = scanner.nextInt();
                    System.out.print("Inserisci numero di pagine: ");
                    int numeroPagineRivista = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci periodicita (SETTIMANALE, MENSILE, SEMESTRALE): ");
                    Periodicita periodicitaRivista = Periodicita.valueOf(scanner.nextLine());
                    CatalogoBibliotecario rivista = new Rivista(isbnRivista, titoloRivista, annoPubblicazioneRivista, numeroPagineRivista, periodicitaRivista);
                    try {
                        archivio.aggiungiElemento(rivista);
                        logger.info("Rivista aggiunta con successo");
                        System.out.println("Rivista aggiunta con successo!\n");
                    } catch (Exception e) {
                        logger.error("Errore nell'aggiunta della rivista: " + e.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    logger.info("Ricerca per ISBN");
                    System.out.println("\n--- Ricerca per ISBN ---");
                    System.out.print("Ricerca per ISBN: ");
                    String isbnRicerca = scanner.nextLine();
                    try {
                        archivio.ricercaPerIsbn(isbnRicerca);
                        logger.info("Ricerca per ISBN completata");
                        System.out.println("Ricerca completata!\n");
                    } catch (Exception e) {
                        logger.error("Errore nella ricerca per ISBN: " + e.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    logger.info("Ricerca per autore");
                    System.out.println("\n--- Ricerca per Autore ---");
                    System.out.print("Ricerca per autore: ");
                    String autoreRicerca = scanner.nextLine();
                    try {
                        archivio.ricercaPerAutore(autoreRicerca);
                        logger.info("Ricerca per autore completata");
                        System.out.println("Ricerca completata!\n");
                    } catch (Exception e) {
                        logger.error("Errore nella ricerca per autore: " + e.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    logger.info("Ricerca per anno di pubblicazione");
                    System.out.println("\n--- Ricerca per Anno di Pubblicazione ---");
                    System.out.print("Ricerca per anno di pubblicazione: ");
                    int annoRicerca = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        archivio.ricercaPerAnnoPubblicazione(annoRicerca);
                        logger.info("Ricerca per anno di pubblicazione completata");
                        System.out.println("Ricerca completata!\n");
                    } catch (Exception e) {
                        logger.error("Errore nella ricerca per anno di pubblicazione: " + e.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    logger.info("Rimuovi elemento");
                    System.out.println("\n--- Rimuovi Elemento ---");
                    System.out.print("Inserisci ISBN dell'elemento da rimuovere: ");
                    String isbnRimuovi = scanner.nextLine();
                    try {
                        archivio.rimuoviElemento(isbnRimuovi);
                        logger.info("Elemento rimosso con successo");
                        System.out.println("Elemento rimosso con successo!\n");
                    } catch (Exception e) {
                        logger.error("Errore nella rimozione dell'elemento: " + e.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    logger.info("Modifica elemento");
                    System.out.println("\n--- Modifica Elemento ---");
                    System.out.print("Inserisci ISBN dell'elemento da modificare: ");
                    String isbnModifica = scanner.nextLine();

                    CatalogoBibliotecario elementoDaModificare = null;
                    try {
                        elementoDaModificare = archivio.ricercaPerIsbn(isbnModifica);
                    } catch (ElementoNonTrovatoException e) {
                        logger.error("Elemento non trovato: " + e.getMessage());
                        System.out.println(e.getMessage());
                        break;
                    }

                    if (elementoDaModificare instanceof Libro) {
                        logger.info("Modifica libro");
                        Libro libroDaModificare = (Libro) elementoDaModificare;

                        System.out.println("Elemento trovato: " + libroDaModificare);
                        System.out.print("Inserisci nuovo ISBN: ");
                        String nuovoIsbn = scanner.nextLine();
                        System.out.print("Inserisci nuovo titolo: ");
                        String nuovoTitolo = scanner.nextLine();
                        System.out.print("Inserisci nuovo anno di pubblicazione: ");
                        int nuovoAnno = scanner.nextInt();
                        System.out.print("Inserisci nuove pagine: ");
                        int nuovePagine = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Inserisci autore: ");
                        String nuovoAutore = scanner.nextLine();
                        System.out.print("Inserisci genere: ");
                        String nuovoGenere = scanner.nextLine();

                        Libro nuovoLibro = new Libro(nuovoIsbn, nuovoTitolo, nuovoAnno, nuovePagine, nuovoAutore, nuovoGenere);

                        try {
                            archivio.modificaElemento(isbnModifica, nuovoLibro);
                            logger.info("Libro modificato con successo");
                            System.out.println("Libro modificato con successo!\n");
                        } catch (ElementoNonTrovatoException e) {
                            logger.error("Errore nella modifica del libro: " + e.getMessage());
                            System.out.println(e.getMessage());
                        }
                    }
                    else if (elementoDaModificare instanceof Rivista) {
                        logger.info("Modifica rivista");
                        Rivista rivistaDaModificare = (Rivista) elementoDaModificare;

                        System.out.println("Elemento trovato: " + rivistaDaModificare);
                        System.out.print("Inserisci nuovo ISBN: ");
                        String nuovoIsbn = scanner.nextLine();
                        System.out.print("Inserisci nuovo titolo: ");
                        String nuovoTitolo = scanner.nextLine();
                        System.out.print("Inserisci nuovo anno di pubblicazione: ");
                        int nuovoAnno = scanner.nextInt();
                        System.out.print("Inserisci nuove pagine: ");
                        int nuovePagine = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Inserisci la periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                        String periodicitaInput = scanner.nextLine();
                        Periodicita nuovaPeriodicita = Periodicita.valueOf(periodicitaInput.toUpperCase());

                        Rivista nuovaRivista = new Rivista(nuovoIsbn, nuovoTitolo, nuovoAnno, nuovePagine, nuovaPeriodicita);

                        try {
                            archivio.modificaElemento(isbnModifica, nuovaRivista);
                            logger.info("Rivista modificata con successo");
                            System.out.println("Rivista modificata con successo!\n");
                        } catch (ElementoNonTrovatoException e) {
                            logger.error("Errore nella modifica della rivista: " + e.getMessage());
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 8:
                    logger.info("Stampa statistiche");
                    System.out.println("\n--- Stampa Statistiche ---");
                    archivio.stampaStatisticheCatalogo();
                    logger.info("Statistiche stampate");
                    System.out.println("Statistiche stampate!\n");
                    break;
                case 9:
                    logger.info("Uscita dal programma");
                    System.out.println("\n--- Uscita ---");
                    System.out.println("Uscita dal programma.");
                    scanner.close();
                    System.exit(0);
                case 10:
                    logger.info("Stampa catalogo");
                    System.out.println("\n--- Stampa Catalogo ---");

                    archivio.stampaCatalogo();
                    logger.info("Catalogo stampato");
                    System.out.println("Catalogo stampato!\n");
                    break;
                default:
                    logger.warn("Opzione non valida scelta");
                    System.out.println("Opzione non valida. Riprova.\n");
            }
        }
    }
}

