package br.com.matheusdacruz.cm.view;

import br.com.matheusdacruz.cm.model.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        initComponents();
    }

    private void initComponents(){
        Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);
        add(new PainelTabuleiro(tabuleiro));

        setTitle("Campo Minado");
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}
