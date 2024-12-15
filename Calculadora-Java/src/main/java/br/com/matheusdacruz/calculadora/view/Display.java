package br.com.matheusdacruz.calculadora.view;

import br.com.matheusdacruz.calculadora.model.Memoria;
import br.com.matheusdacruz.calculadora.model.MemoriaObservador;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel implements MemoriaObservador {
    private JLabel jl1;

    public Display() {
        Memoria.getInstancia().adicionarObservador(this);
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(46, 49, 50));
        jl1 = new JLabel(Memoria.getInstancia().getTextoAtual());
        jl1.setForeground(Color.WHITE);
        jl1.setFont(new Font("courier", Font.PLAIN, 30));

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
        add(jl1);
    }

    @Override
    public void valorAlterado(String novoValor) {
        jl1.setText(novoValor);
    }
}
