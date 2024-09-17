package view;

import controller.Pessoa;

public class Principal {
    public static void main(String[] args) {
        Pessoa[] pessoas = {
            new Pessoa("Pessoa 1"),
            new Pessoa("Pessoa 2"),
            new Pessoa("Pessoa 3"),
            new Pessoa("Pessoa 4")
        };

        for (Pessoa pessoa : pessoas) {
            pessoa.start();
        }

        // Espera todas as threads terminarem
        for (Pessoa pessoa : pessoas) {
            try {
                pessoa.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Simulação concluída.");
    }
}
