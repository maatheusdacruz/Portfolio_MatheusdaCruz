package br.com.matheusdacruz.calculadora.view;

import br.com.matheusdacruz.calculadora.model.Memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teclado extends JPanel implements ActionListener {

    private Color COLOR_DARKGRAY = new Color(68, 68, 68);
    private Color COLOR_GRAY = new Color(99, 99, 99);
    private Color COLOR_ORANGE = new Color(242, 163, 60);

    public Teclado() {
        initComponents();
    }

    private void initComponents() {

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        setLayout(gridbag);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridwidth = 2;
        adicionarBotao("AC", COLOR_GRAY, c, 0, 0);
        c.gridwidth = 1;
        adicionarBotao("+/-", COLOR_GRAY, c, 2, 0);
        adicionarBotao("/", COLOR_ORANGE, c, 3, 0);

        adicionarBotao("7", COLOR_DARKGRAY, c, 0, 1);
        adicionarBotao("8", COLOR_DARKGRAY, c, 1, 1);
        adicionarBotao("9", COLOR_DARKGRAY, c, 2, 1);
        adicionarBotao("*", COLOR_ORANGE, c, 3, 1);

        adicionarBotao("4", COLOR_DARKGRAY, c, 0, 2);
        adicionarBotao("5", COLOR_DARKGRAY, c, 1, 2);
        adicionarBotao("6", COLOR_DARKGRAY, c, 2, 2);
        adicionarBotao("-", COLOR_ORANGE, c, 3, 2);

        adicionarBotao("1", COLOR_DARKGRAY, c, 0, 3);
        adicionarBotao("2", COLOR_DARKGRAY, c, 1, 3);
        adicionarBotao("3", COLOR_DARKGRAY, c, 2, 3);
        adicionarBotao("+", COLOR_ORANGE, c, 3, 3);

        c.gridwidth = 2;
        adicionarBotao("0", COLOR_DARKGRAY, c, 0, 4);
       c.gridwidth = 1;
        adicionarBotao(",", COLOR_DARKGRAY, c, 2, 4);
        adicionarBotao("=", COLOR_ORANGE, c, 3, 4);
    }

    private void adicionarBotao(String text, Color color, GridBagConstraints c, int x, int y) {
        c.gridx = x;
        c.gridy = y;
        Botao botao = new Botao(text, color);
        botao.addActionListener(this);
        add(botao, c);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){
            JButton botao = (JButton) e.getSource();
            Memoria.getInstancia().processarComando(botao.getText());
        }
    }
}
