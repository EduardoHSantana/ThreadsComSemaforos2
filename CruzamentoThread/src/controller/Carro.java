package controller;

import java.util.concurrent.Semaphore;

public class Carro extends Thread {
    private String sentido;
    private Semaphore cruzamento;

    public Carro(String sentido, Semaphore cruzamento) {
        this.sentido = sentido;
        this.cruzamento = cruzamento;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void run() {
        try {
            // Carro tenta acessar o cruzamento
            cruzamento.acquire(); 
            System.out.println("Carro " + getId() + " está passando pelo cruzamento. Sentido: " + sentido);
            // Simulando o tempo que o carro leva para atravessar o cruzamento
            Thread.sleep(1000); // 1 segundo para atravessar
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Carro " + getId() + " saiu do cruzamento. Sentido: " + sentido);
            cruzamento.release(); // Libera o cruzamento para o próximo carro
        }
    }
}
