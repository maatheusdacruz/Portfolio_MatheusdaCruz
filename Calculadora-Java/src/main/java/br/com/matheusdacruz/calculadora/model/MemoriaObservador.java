package br.com.matheusdacruz.calculadora.model;
@FunctionalInterface
public interface MemoriaObservador {
    void valorAlterado(String novoValor);
}
