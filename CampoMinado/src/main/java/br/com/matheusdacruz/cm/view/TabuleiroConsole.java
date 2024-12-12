package br.com.matheusdacruz.cm.view;

import br.com.matheusdacruz.cm.exception.ExplosaoException;
import br.com.matheusdacruz.cm.exception.SairException;
import br.com.matheusdacruz.cm.model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    private Scanner scanner = new Scanner(System.in);
    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try{

            boolean continuar = true;
            while (continuar) {
                cicloDoJogo();


                System.out.println("Outra partida? (S/N)");
                String r = scanner.nextLine();

                if("n".equalsIgnoreCase(r)) {
                    continuar = false;
                }
                else if("s".equalsIgnoreCase(r)) {
                    tabuleiro.reiniciar();
                }
            }
        }catch (SairException e){
            System.out.println(e.getMessage() + " Tchau");
        }finally {
            scanner.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while(!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);
                String digitado = capturarValorDigitado("Digite(x, y):  ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e-> Integer.parseInt(e.trim()))
                        .iterator();

                digitado = capturarValorDigitado("1- Abrir ou 2 - (Des)Marcar: ");
                if("1".equalsIgnoreCase(digitado)){
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if("2".equalsIgnoreCase(digitado)){
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }
            }
            System.out.println(tabuleiro);
            System.out.println("Você Ganhou");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Você perdeu");
        }
    }
    private String capturarValorDigitado(String texto) {
        System.out.println(texto);
        String digitado = scanner.nextLine();

        if("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}
