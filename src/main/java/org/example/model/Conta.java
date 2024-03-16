package org.example.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Conta {

    private String numero;
    private Double saldo;
    private final List<Conta> contas = new ArrayList<>();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final String formatSeparadorTela = "=".repeat(100);
    private String contaAtual;

    public void deposito() throws IOException {
        Double saldoAtual = 0.0;
        System.out.println("Digite o numero da conta: ");
        this.contaAtual = reader.readLine();

        System.out.println("Digite o valor do deposito: ");
        double saldo = Double.parseDouble(reader.readLine());

        if (this.getSaldo() != null ) {
            saldoAtual =this.getSaldo();
        }

        this.setDeposito(saldoAtual + saldo, this.contaAtual);
        this.contas.add(this);
        reader.close();
    }

    public void historico () throws IOException {

        double saldo = 0.0;

        System.out.println(this.formatSeparadorTela);
        System.out.println("Digite o numero da conta.");
        String numeroConta = this.reader.readLine();
        reader.close();

        for (Conta conta : this.contas) {
            if (conta.getNumero().equals(numeroConta)) {
                saldo = conta.getSaldo();
            }
        }

        System.out.println("\n"+this.formatSeparadorTela);
        System.out.println("Historico de Conta");
        System.out.println("Conta: "+this.contaAtual);
        System.out.println("Saldo atual: "+saldo);
        System.out.println(this.formatSeparadorTela);
    }

    public void sacar() throws IOException {
        System.out.println("Digite o numero da conta para sacar: ");
        String numeroConta = reader.readLine();

        for (Conta conta : this.contas) {
            if (conta.getNumero().equals(numeroConta)) {
                this.contaAtual = numeroConta;
                System.out.println("Digite o valor para saque: ");
                Double valorSaque = Double.parseDouble(reader.readLine());
                reader.close();
                Double saldo = conta.getSaldo();
                if (saldo >= valorSaque) {
                    var saldoFinal = conta.getSaldo() - valorSaque;
                    conta.setDeposito(saldoFinal, conta.getNumero());
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.err.println("Saque não realizado.");
                }
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    public void transferencia() throws IOException {

        System.out.println(this.formatSeparadorTela);
        double valorTransferir = Double.parseDouble(this.reader.readLine());
        String numeroContaEnviarInput = this.reader.readLine();
        String numeroContaReceberInput = this.reader.readLine();
        System.out.println(this.formatSeparadorTela);
        reader.close();

        List<Conta> listContas = new ArrayList<Conta>();
        for (Conta conta : this.contas) {
            if (conta.getNumero().equals(numeroContaEnviarInput) || conta.getNumero().equals(numeroContaReceberInput)) {
                listContas.add(conta);
            }
        }

        if (listContas.get(0).getNumero() == numeroContaEnviarInput) {
            this.executeOperation(listContas.get(0),listContas.get(1), valorTransferir);
        }else {
            this.executeOperation(listContas.get(1),listContas.get(0), valorTransferir);
        }

    }

    private void executeOperation (Conta contaEnviar, Conta contaReceber, double valorTransferir) throws IOException {
        Double saldoAtual = contaEnviar.getSaldo();
        Double atualizaSaldo;

        if (saldoAtual < valorTransferir) {
            throw  new Error("Saldo indisponível.");
        }

        atualizaSaldo = saldoAtual - valorTransferir;
        contaEnviar.setDeposito(atualizaSaldo, contaEnviar.getNumero());
        Double valorReceber = (contaReceber.getSaldo() + atualizaSaldo);
        contaReceber.setDeposito(valorReceber, contaReceber.getNumero());

        System.out.println(this.formatSeparadorTela);
        System.out.println("Transferencia feita entre as contas");
        System.out.printf("\nConta a receber: %s ", contaReceber.getNumero());
        System.out.printf("\nConta cedente: %s ", contaReceber.getNumero());
        System.out.printf("\nValor transferido: %s ", valorTransferir);
        System.out.println(this.formatSeparadorTela);
    }

    public void listarContas() throws IOException {
        if (contas.isEmpty()) {
            System.out.println("\n"+this.formatSeparadorTela+"\n\nNão existe contas.\n\n"+this.formatSeparadorTela);
        }else {
            System.out.println(this.formatSeparadorTela);
            System.out.println("Total de contas cadastradas: "+contas.size());
            System.out.println(this.formatSeparadorTela);

            for (Conta conta : this.contas) {
                System.out.println("CONTA: "+conta.getNumero());
            }

            System.out.println(this.formatSeparadorTela);
        }
        reader.readLine();
        reader.close();
    }

    private void setDeposito (Double valor, String numero) throws IOException {
        this.setNumero(numero);
        this.setSaldo(valor);
    }

    public Double getSaldo() {
        return this.saldo;
    }

    private void setNumero (String numero)  {
        this.numero = numero;
    }

    public String getNumero() {
        return this.numero;
    }
    private void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
