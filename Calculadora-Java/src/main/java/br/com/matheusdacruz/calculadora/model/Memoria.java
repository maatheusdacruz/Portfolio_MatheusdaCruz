package br.com.matheusdacruz.calculadora.model;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private static Memoria instancia = new Memoria();
    private final List<MemoriaObservador> observadores = new ArrayList<>();
    private TipoComando ultimaOperacao = null;
    private boolean substituir = false;
    private String textoAtual = "";
    private String textBuffer = "";
    private enum TipoComando {
          ZERAR, SINAL, NUMERO, DIV, MULT, SUB, SOMA, PORCENTO, IGUAL, VIRGULA;
    };
    private Memoria(){

    }

    public static Memoria getInstancia() {
        return instancia;
    }

    public void adicionarObservador(MemoriaObservador observador) {
        observadores.add(observador);
    }

    public String getTextoAtual() {
        return textoAtual.isEmpty()? "0": textoAtual;
    }

    public void processarComando(String comando) {

        TipoComando tipo = detectarTipoComando(comando);

        if(tipo == null){
            return;
        }else if(tipo == TipoComando.ZERAR){
            textoAtual = "";
            textBuffer = "";
            substituir = false;
            ultimaOperacao = null;
        } else if(tipo == TipoComando.NUMERO || tipo == TipoComando.VIRGULA){
            textoAtual = substituir? comando : textoAtual + comando;
            substituir = false;
        } else if(tipo == TipoComando.SINAL && textoAtual.contains("-")){
            textoAtual = textoAtual.substring(1);
        }else if(tipo == TipoComando.SINAL && !textoAtual.contains("-")){
            textoAtual = "-" + textoAtual;
        } else if(tipo == TipoComando.PORCENTO) {
            substituir = true;
            textBuffer = textoAtual;
            ultimaOperacao = tipo;
            textoAtual = resultadoOperacao();
            ultimaOperacao = null;
            substituir = false;
        } else {
            substituir = true;
            textoAtual = resultadoOperacao();
            textBuffer = textoAtual;
            ultimaOperacao = tipo;
        }

        observadores.forEach(o-> o.valorAlterado(getTextoAtual()));
    }

    private String resultadoOperacao() {
        if(ultimaOperacao == null
        || ultimaOperacao == TipoComando.IGUAL){
            return textoAtual;
        }
        double numeroBuffer = Double.parseDouble(textBuffer.replace(",", "."));
        double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));

        double resultado = 0;

        if(ultimaOperacao == TipoComando.SOMA){
            resultado = numeroBuffer + numeroAtual;
        } else if(ultimaOperacao == TipoComando.DIV){
            resultado = numeroBuffer / numeroAtual;
        }else if(ultimaOperacao == TipoComando.PORCENTO){
            resultado = numeroBuffer / 100;
        }else if(ultimaOperacao == TipoComando.MULT){
            resultado = numeroBuffer * numeroAtual;
        }else if(ultimaOperacao == TipoComando.SUB){
            resultado = numeroBuffer - numeroAtual;
        }

        String resultadoString = Double.toString(resultado).replace(".", ",");
        boolean inteiro = resultadoString.endsWith(",0");

        return inteiro? resultadoString.replace(",0",  "") : resultadoString;
    }

    private TipoComando detectarTipoComando(String comando) {
        if(textoAtual.isEmpty() && comando == "0"){
            return null;
        }
        try {
        Integer.parseInt(comando);
        return TipoComando.NUMERO;
        } catch (NumberFormatException e) {
            if("AC".equals(comando)){
                return TipoComando.ZERAR;
            }else if("+/-".equals(comando)){
                return TipoComando.SINAL;
            }else if("%".equals(comando)){
                return TipoComando.PORCENTO;
            } else if("/".equals(comando)){
                return TipoComando.DIV;
            } else if("*".equals(comando)){
                return TipoComando.MULT;
            }else if("-".equals(comando)){
                return TipoComando.SUB;
            }else if("+".equals(comando)){
                return TipoComando.SOMA;
            } else if("=".equals(comando)){
                return TipoComando.IGUAL;
            } else if(",".equals(comando) && !textoAtual.contains(",")){
                return TipoComando.VIRGULA;
            }
        }
        return null;
    }
}
