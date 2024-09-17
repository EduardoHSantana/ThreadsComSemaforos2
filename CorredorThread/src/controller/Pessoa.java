package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Pessoa extends Thread {
    private static final Random random = new Random();
    private static final Semaphore porta = new Semaphore(1); // Apenas uma pessoa pode passar pela vez

    private final String nome;
    private final int comprimentoCorredor = 200; // Comprimento do corredor em metros

    public Pessoa(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        try {
            // Simula o percurso pelo corredor
            double velocidade = 4 + (2 * random.nextDouble()); // Velocidade entre 4 e 6 m/s
            double tempoParaChegarNaPorta = comprimentoCorredor / velocidade;
            System.out.println(nome + " está caminhando pelo corredor a " + velocidade + " m/s.");

            // Atraso para percorrer o corredor
            Thread.sleep((long) (tempoParaChegarNaPorta * 1000));

            // Tentando passar pela porta
            System.out.println(nome + " está tentando abrir a porta.");
            porta.acquire(); // Espera a vez de passar

            // Tempo para abrir e atravessar a porta
            int tempoParaPassarAPorta = 1 + random.nextInt(2); // Tempo entre 1 e 2 segundos
            System.out.println(nome + " está passando pela porta.");
            Thread.sleep(tempoParaPassarAPorta * 1000);

            System.out.println(nome + " passou pela porta.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            porta.release(); // Libera a porta para a próxima pessoa
        }
    }
}
