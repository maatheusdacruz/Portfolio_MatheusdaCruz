package br.com.matheusdacruz.cm.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class Tabuleiro_teste {
    private Tabuleiro tabuleiro;

    @BeforeEach
    void setUp() {
        tabuleiro = new Tabuleiro(6, 6, 3);
    }
    @Test
    void testabrir(){
        Campo campo = new Campo(2, 3);

        int i = 0;
        externo: for(int l = 0; l< tabuleiro.getLinhas(); l++){
            for(int c= 0; c< tabuleiro.getColunas(); c++){
                if(l == campo.getLinha() && c == campo.getColuna()){
                    break externo;
                }
                i++;
            }
        }

        tabuleiro.abrir(campo.getLinha(), campo.getColuna());

        assertTrue(tabuleiro.getCampos().get(i).isAberto());
    }

    @Test
    void testcampomarcado(){
        Campo campo = new Campo(2, 3);

        int i = 0;
        externo: for(int l = 0; l< tabuleiro.getLinhas(); l++){
            for(int c= 0; c< tabuleiro.getColunas(); c++){
                if(l == campo.getLinha() && c == campo.getColuna()){
                    break externo;
                }
                i++;
            }

        }
        tabuleiro.alternarMarcacao(campo.getLinha(), campo.getColuna());

        assertTrue(tabuleiro.getCampos().get(i).isMarcado());
    }
    @Test
    void testcampodesmarcado(){
        Campo campo = new Campo(2, 3);

        int i = 0;
        externo: for(int l = 0; l< tabuleiro.getLinhas(); l++){
            for(int c= 0; c< tabuleiro.getColunas(); c++){
                if(l == campo.getLinha() && c == campo.getColuna()){
                    break externo;
                }
                i++;
            }

        }
        tabuleiro.alternarMarcacao(campo.getLinha(), campo.getColuna());
        tabuleiro.alternarMarcacao(campo.getLinha(), campo.getColuna());

        assertFalse(tabuleiro.getCampos().get(i).isMarcado());
    }

    @Test
    void testObjetivoAlcancadoFalse(){
        boolean a = tabuleiro.objetivoAlcancado();
        assertFalse(a);
    }
    @Test
    void testObjetivoAlcancadoTrue(){
        for(Campo c : tabuleiro.getCampos()){
            if(!c.isMinado()){
                c.setAberto(true);
            } else if(c.isMinado()){
                c.alternarMarcacao();
            }
        }
        boolean a = tabuleiro.objetivoAlcancado();
        assertTrue(a);
    }

    @Test
    void testReiniciar(){
        tabuleiro.reiniciar();
        boolean a = tabuleiro.getCampos().stream().allMatch(c-> !c.isAberto() && !c.isMarcado() && !c.isMinado());
        assertFalse(a);
    }

    @Test
    void testGetLinhas(){
        boolean a;
        int i;
        for(i = 0; i < tabuleiro.getLinhas(); i++){
        }
        a = i == tabuleiro.getLinhas();;

        assertTrue(a);
    }
    @Test
    void testGetColunas(){
        boolean a;
        int i;
        for(i = 0; i < tabuleiro.getColunas(); i++){
        }
        a = i == tabuleiro.getColunas();;

        assertTrue(a);
    }

}
