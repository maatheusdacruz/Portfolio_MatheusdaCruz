package br.com.matheusdacruz.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements BiConsumer<Campo, CampoEvento> {
    private int linhas;
    private int colunas;
    private int minas;
    
    private final List<Campo> campos = new ArrayList<Campo>();
    private final List<Consumer<ResultadoGanhou>> observadores = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;
        
        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public void registraObservador(Consumer<ResultadoGanhou> observador) {
        observadores.add(observador);
    }
    private void notificarObservador(boolean resultado){
        observadores.stream()
                .forEach(o-> o.accept(new ResultadoGanhou(resultado)));
    }

    public int getMinas() {
        return minas;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void abrir(int linha, int coluna) {
           campos.parallelStream()
                   .filter(c-> c.getLinha() == linha
                           && c.getColuna() == coluna)
                   .findFirst()
                   .ifPresent(c -> c.abrir());
    }

    public void alternarMarcacao(int linha, int coluna) {
        campos.parallelStream()
                .filter( c -> c.getLinha() == linha
                && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c-> c.alternarMarcacao());
    }

    private void gerarCampos() {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                Campo campo = new Campo(l, c);
                campo.registrarObservador(this);
                campos.add(campo);
            }
        }
    }

    private void associarVizinhos() {
        for(Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado = c-> c.isMinado();

        do{
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();
        }while (minasArmadas < minas);

    }
    public boolean objetivoAlcancado(){
        return campos.stream().allMatch(c-> c.objetivoAlcancado());
    }

    public void reiniciar(){
        campos.stream().forEach(c-> c.reiniciar());
        sortearMinas();
    }
    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    @Override
    public void accept(Campo campo, CampoEvento evento) {
        if(evento == CampoEvento.EXPLODIR){
            mostrarMinas();
            notificarObservador(false);
        } else if (objetivoAlcancado()) {
            notificarObservador(true);
        }
    }

    private void mostrarMinas() {
        campos.stream()
                .filter(c -> c.isMinado())
                .filter(c-> !c.isMarcado())
                .forEach(c-> c.setAberto(true));
    }

    public void paraCada(Consumer<Campo> funcao){
        campos.forEach(funcao);
    }

}
