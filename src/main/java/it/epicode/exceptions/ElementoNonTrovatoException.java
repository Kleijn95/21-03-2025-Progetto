package it.epicode.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementoNonTrovatoException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(ElementoNonTrovatoException.class);

    public ElementoNonTrovatoException(String message) {
        super(message);
        logger.error("Elemento non trovato: {}", message);
    }
}
