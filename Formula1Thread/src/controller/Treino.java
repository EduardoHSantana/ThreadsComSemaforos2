package controller;

import java.util.concurrent.Semaphore;

public class Treino {
    private static final int MAX_CARROS_PISTA = 5;
    private static final int NUM_VOLETAS = 3;
    private static final Semaphore pistaSemaphore = new Semaphore(MAX_CARROS_PISTA);
    private final Volta[] voltas;
    private final Carro[] carros;

    public Treino(Carro[] carros) {
        this.carros = carros;
        this.voltas = new Volta[carros.length * NUM_VOLETAS];
    }

    public void iniciarTreino() {
        Thread[] threads = new Thread[carros.length];
        for (int i = 0; i < carros.length; i++) {
            Carro carro = carros[i];
            threads[i] = new Thread(() -> realizarVoltas(carro));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exibirResultados();
    }

    private void realizarVoltas(Carro carro) {
        try {
            for (int i = 0; i < NUM_VOLETAS; i++) {
                pistaSemaphore.acquire();
                double tempo = gerarTempoVolta();
                System.out.println(carro.getPiloto() + " (" + carro.getEquipe() + ") fez uma volta em " + tempo + " segundos.");
                registrarVolta(carro, tempo);
                Thread.sleep((long) (tempo * 1000)); // Simula o tempo gasto na volta
                pistaSemaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double gerarTempoVolta() {
        // Gera um tempo aleatório entre 60 e 120 segundos
        return 60 + (60 * Math.random());
    }

    private void registrarVolta(Carro carro, double tempo) {
        int posicao = encontrarPosicaoInsercao(tempo);
        // Move os elementos para abrir espaço para a nova volta
        if (posicao < voltas.length) {
            for (int i = voltas.length - 1; i > posicao; i--) {
                voltas[i] = voltas[i - 1];
            }
            voltas[posicao] = new Volta(carro, tempo);
        }
    }

    private int encontrarPosicaoInsercao(double tempo) {
        int i = 0;
        while (i < voltas.length && voltas[i] != null && voltas[i].getTempo() <= tempo) {
            i++;
        }
        return i;
    }

    private void exibirResultados() {
        System.out.println("Grid de Largada:");
        for (Volta volta : voltas) {
            if (volta != null) {
                System.out.println(volta.getCarro().getPiloto() + " (" + volta.getCarro().getEquipe() + ") - Tempo: " + volta.getTempo() + " segundos");
            }
        }
    }
}
