package br.com.matheusdacruz.calculadora.view;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    private JLabel jl1;

    public Display() {
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(46, 49, 50));
        jl1 = new JLabel("1234,56");
        jl1.setForeground(Color.WHITE);
        jl1.setFont(new Font("courier", Font.PLAIN, 30));

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
        add(jl1);
    }
}
