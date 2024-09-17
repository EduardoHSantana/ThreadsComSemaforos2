package view;

import controller.Carro;
import controller.Treino;

public class Principal {
    public static void main(String[] args) {
        Carro[] carros = new Carro[14];

        // Adiciona 7 escuderias com 2 carros cada
        for (int i = 1; i <= 7; i++) {
            String equipe = "Escuderia " + i;
            carros[(i - 1) * 2] = new Carro(equipe, "Piloto " + i + " - Carro 1");
            carros[(i - 1) * 2 + 1] = new Carro(equipe, "Piloto " + i + " - Carro 2");
        }

        Treino treino = new Treino(carros);
        treino.iniciarTreino();
    }
}
