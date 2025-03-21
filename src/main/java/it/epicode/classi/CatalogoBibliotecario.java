package it.epicode.classi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
@Getter
@Data
public abstract class CatalogoBibliotecario {
    protected String isbn;
    protected String titolo;
    protected int annoPubblicazione;
    protected int numeroPagine;


}

