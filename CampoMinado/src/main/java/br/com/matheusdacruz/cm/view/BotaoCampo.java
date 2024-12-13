package br.com.matheusdacruz.cm.view;

import br.com.matheusdacruz.cm.model.Campo;
import br.com.matheusdacruz.cm.model.CampoEvento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.BiConsumer;

public class BotaoCampo extends JButton implements BiConsumer<Campo, CampoEvento>, MouseListener {

    private Campo campo;
    private final Color BG_Padrao = new Color(184, 184, 184);
    private final Color BG_Marcado = new Color(8, 179, 247);
    private final Color BG_Explosao = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);

    public BotaoCampo(Campo campo) {
        this.campo = campo;
        setBackground(BG_Padrao);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(0));

        addMouseListener(this);
        campo.registrarObservador(this);
    }

    @Override
    public void accept(Campo campo, CampoEvento evento) {
        switch (evento){
            case ABRIR:
                aplicarEstiloAbrir();
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;
            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
                default:
                    aplicarEstiloPadrao();
        }
        SwingUtilities.invokeLater(() -> {
            repaint();
            validate();
        });
    }

    private void aplicarEstiloPadrao() {
        setBackground(BG_Padrao);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");
    }

    private void aplicarEstiloExplodir() {
        setBackground(BG_Explosao);
        setForeground(Color.WHITE);
        setText("X");
    }

    private void aplicarEstiloMarcar() {
        setBackground(BG_Marcado);
        setForeground(Color.BLACK);
        setText("M");
    }

    private void aplicarEstiloAbrir() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        if(campo.isMinado()){
            setBackground(BG_Explosao);
            setText("X");
            return;
        }

        setBackground(BG_Padrao);
        switch (campo.minasNaVizinhanca()){
            case 1:
                setForeground(TEXTO_VERDE);
                break;
                case 2:
                    setForeground(Color.BLUE);
                    break;
                    case 3:
                        setForeground(Color.YELLOW);
                        break;
                        case 4: case 5: case 6:
                            setForeground(Color.RED);
                            break;
                            default:
                                setForeground(Color.PINK);
        }
        String valor = !campo.vizinhancaSegura() ?
                campo.minasNaVizinhanca() + "": "";
        setText(valor);

    }


    //Mouse


    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1){
            campo.abrir();
        }else{
            campo.alternarMarcacao();
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
