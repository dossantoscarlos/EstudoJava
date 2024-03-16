package org.example.ci;

import org.example.model.Conta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TelaCi {
    private final String formatSeparadorTela = "=".repeat(100);

    public void execute() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int cont = 1;
            Conta conta = new Conta();
            do {

                System.out.println("\n"+ this.formatSeparadorTela);
                System.out.print("Escolha uma opção:\n0. Contas\n1. Deposito\n2. Sacar\n3. Historico\n4. Transferencia\n5. Sair\n");
                System.out.println(this.formatSeparadorTela);

                var op = Integer.parseInt(reader.readLine());
                switch (op) {
                    case 0:
                        conta.listarContas();
                        break;
                    case 1:
                        conta.deposito();
                        break;
                    case 2:
                        conta.sacar();
                        break;
                    case 3:
                        conta.historico();
                        break;
                    case 4:
                        conta.transferencia();
                        break;
                    default:
                        cont = 0;
                        break;
                }
            }while(cont != 0);

        } catch (IOException e) {
            System.out.println("Erro de entrada ou saída: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Formato de entrada inválido.");
        }finally {
            reader.close();
        }
    }
}
