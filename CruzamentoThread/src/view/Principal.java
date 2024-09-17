package view;

import controller.Carro;
import java.util.concurrent.Semaphore;

public class Principal {
    public static void main(String[] args) {
        // Criar um semáforo que permite apenas um carro no cruzamento
        Semaphore cruzamento = new Semaphore(1);
        
        // Criar carros com seus respectivos sentidos
        Carro carro1 = new Carro("Norte", cruzamento);
        Carro carro2 = new Carro("Sul", cruzamento);
        Carro carro3 = new Carro("Leste", cruzamento);
        Carro carro4 = new Carro("Oeste", cruzamento);
        
        // Iniciar as threads (carros)
        carro1.start();
        carro2.start();
        carro3.start();
        carro4.start();
    }
}
