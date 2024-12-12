package br.com.matheusdacruz.cm.model;
import br.com.matheusdacruz.cm.exception.ExplosaoException;
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
    @Test
    void testeValorPadraoAtributoMarcado(){
        assertFalse(campo.isMarcado());
    }

//    @Test
//    void testeAlternarMarcacao(){
//        campo.alternarMarcacao();
//        assertFalse(campo.isMarcado());
//    }
    @Test
    void alternarMarcacaoDuasChamadas(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse((campo.isMarcado()));
    }

    @Test
    void testeAbrirNaoMInadoNaoMarcado(){
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbirNaoMinadoMarcado(){
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }


    @Test
    void testeAbrirMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado(){
        campo.minar();

        assertThrows(ExplosaoException.class, () -> campo.abrir());
    }

    @Test
    void testeAbrirComVizinhos(){
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 1);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo11.isFechado() && campo22.isAberto());
    }
}
