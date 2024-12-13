package br.com.matheusdacruz.cm.model;

public class ResultadoGanhou {
    private final boolean ganhou;

    public ResultadoGanhou(boolean ganhou) {
        this.ganhou = ganhou;
    }

    public boolean isGanhou() {
        return ganhou;
    }
}
