package controller;

public class Conta {
    private int codigo;
    private double saldo;

    public Conta(int codigo, double saldo) {
        this.codigo = codigo;
        this.saldo = saldo;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void realizarSaque(double valor) {
        // Verifica saldo e realiza saque
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de " + valor + " realizado. Saldo atual: " + saldo);
        } else {
            System.out.println("Saldo insuficiente para saque de " + valor);
        }
    }

    public void realizarDeposito(double valor) {
        // Realiza depósito
        saldo += valor;
        System.out.println("Depósito de " + valor + " realizado. Saldo atual: " + saldo);
    }
}
