package br.com.matheusdacruz.cm.view;

import br.com.matheusdacruz.cm.model.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {
    public PainelTabuleiro(Tabuleiro tabuleiro) {
        setLayout(new GridLayout(
                tabuleiro.getLinhas(), tabuleiro.getColunas()));

        tabuleiro.paraCada(c-> add(new BotaoCampo(c)));

        tabuleiro.registraObservador(e-> {

            SwingUtilities.invokeLater(() ->{
                if(e.isGanhou()){
                    JOptionPane.showMessageDialog(null, "Ganhou");
                }else {
                    JOptionPane.showMessageDialog(null, "Perdeu");
                }

                tabuleiro.reiniciar();
            });
        });
    }
}
