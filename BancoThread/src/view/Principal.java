package view;

import controller.Conta;
import controller.Transacao;

public class Principal {
    public static void main(String[] args) {
        Conta conta = new Conta(1, 1000.0); // Conta inicial com saldo de 1000.0
        Transacao[] transacoes = new Transacao[20];

        for (int i = 0; i < transacoes.length; i++) {
            boolean saque = Math.random() < 0.5; // 50% de chance para saque ou depósito
            double valor = 10 + (90 * Math.random()); // Valor aleatório entre 10 e 100
            transacoes[i] = new Transacao(conta, saque, valor);
        }

        for (Transacao transacao : transacoes) {
            transacao.start();
        }

        // Espera todas as threads terminarem
        for (Transacao transacao : transacoes) {
            try {
                transacao.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Simulação concluída.");
    }
}
