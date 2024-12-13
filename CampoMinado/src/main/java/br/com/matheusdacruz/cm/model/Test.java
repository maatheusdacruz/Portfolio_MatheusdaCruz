package br.com.matheusdacruz.cm.model;

public class Test {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(2, 2, 4);
        tabuleiro.alternarMarcacao(1,1);
        tabuleiro.alternarMarcacao(0,1);
        tabuleiro.alternarMarcacao(1,0);
        tabuleiro.alternarMarcacao(0,0);
    }
}
