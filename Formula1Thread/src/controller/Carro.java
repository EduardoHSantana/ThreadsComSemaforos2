package controller;

public class Carro {
    private final String equipe;
    private final String piloto;

    public Carro(String equipe, String piloto) {
        this.equipe = equipe;
        this.piloto = piloto;
    }

    public String getEquipe() {
        return equipe;
    }

    public String getPiloto() {
        return piloto;
    }
}
