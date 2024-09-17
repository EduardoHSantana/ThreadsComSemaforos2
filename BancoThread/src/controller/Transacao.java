package controller;

import java.util.concurrent.Semaphore;

public class Transacao extends Thread {
    private static final Semaphore saqueSemaphore = new Semaphore(1); // Apenas um saque por vez
    private static final Semaphore depositoSemaphore = new Semaphore(1); // Apenas um depósito por vez

    private final Conta conta;
    private final boolean saque;
    private final double valor;

    public Transacao(Conta conta, boolean saque, double valor) {
        this.conta = conta;
        this.saque = saque;
        this.valor = valor;
    }

    @Override
    public void run() {
        try {
            if (saque) {
                saqueSemaphore.acquire(); // Garante que apenas um saque ocorra por vez
                conta.realizarSaque(valor);
            } else {
                depositoSemaphore.acquire(); // Garante que apenas um depósito ocorra por vez
                conta.realizarDeposito(valor);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (saque) {
                saqueSemaphore.release(); // Libera o semáforo para o próximo saque
            } else {
                depositoSemaphore.release(); // Libera o semáforo para o próximo depósito
            }
        }
    }
}
