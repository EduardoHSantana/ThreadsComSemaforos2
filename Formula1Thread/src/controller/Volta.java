package controller;

public class Volta {
    private final Carro carro;
    private final double tempo;

    public Volta(Carro carro, double tempo) {
        this.carro = carro;
        this.tempo = tempo;
    }

    public Carro getCarro() {
        return carro;
    }

    public double getTempo() {
        return tempo;
    }
}
