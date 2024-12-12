package br.com.matheusdacruz.cm;

import br.com.matheusdacruz.cm.model.Tabuleiro;
import br.com.matheusdacruz.cm.view.TabuleiroConsole;

import javax.swing.plaf.TabbedPaneUI;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        new TabuleiroConsole(tabuleiro);


    }
}
