package br.com.matheusdacruz.cm.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class Campo_teste {
    private Campo campo;

    @BeforeEach
    void setUp() {
        campo = new Campo(3, 3);
    }
    @Test
    void testVizinhoReal() {
        Campo vizinho = new Campo(3, 1);

        boolean resultado = campo.adicionarVizinho(vizinho);

        assertFalse(resultado);
    }

}
