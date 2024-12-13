package br.com.matheusdacruz.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Campo {
    private final int linha;
    private final int coluna;

    private boolean minado = false;
    private boolean aberto =false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList();
    private List<BiConsumer<Campo, CampoEvento>> observadores = new ArrayList();


    Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void registrarObservador(BiConsumer observador){
        observadores.add(observador);
    }

    public void notificarObservador(CampoEvento evento){
        observadores.stream()
                .forEach(o-> o.accept(this, evento));
    }

    boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDIferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDIferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if(deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        }else {
            return false;
        }
    }
    public void alternarMarcacao(){
        if(!aberto){
            marcado = !marcado;

            if(marcado){
                notificarObservador(CampoEvento.MARCAR);
            }else {
                notificarObservador(CampoEvento.DESMARCAR);
            }
        }
    }
    public boolean abrir(){
        if(!aberto && !marcado){
            if(minado){
                notificarObservador(CampoEvento.EXPLODIR);
                return true;
            }

            setAberto(true);

            if(vizinhancaSegura()) {
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        }

        return false;
    }
    public boolean vizinhancaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }
    public boolean isMarcado(){
        return marcado;
    }

    void minar(){
        minado = true;
    }

    public boolean isMinado(){
        return minado;
    }


    public boolean isAberto(){
        return aberto;
    }
    public boolean isFechado(){
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    void setAberto(boolean aberto) {
        this.aberto = aberto;

        if(aberto){
            notificarObservador(CampoEvento.ABRIR);
        }
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    public int minasNaVizinhanca(){
        return (int) vizinhos.stream().filter(v-> v.minado).count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
        notificarObservador(CampoEvento.REINICIAR);
    }
}
