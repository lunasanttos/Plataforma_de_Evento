package br.com.teste.app;

import br.com.teste.model.Local;
import br.com.teste.service.LocalService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroLocal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalService service = new LocalService();

        System.out.println(" Cadastro de Local ");


        System.out.print("Nome do Local: ");
        String nomeLocal = scanner.nextLine();

        System.out.print("Endereço: ");
        String enderecoLocal = scanner.nextLine();

        int capacidadeLocal = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print(" Capacidade do Local: ");
                capacidadeLocal = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. ");
                scanner.next();
            }
        }
        scanner.nextLine();


        Local novoLocal = new Local();
        novoLocal.setNome(nomeLocal);
        novoLocal.setEndereco(enderecoLocal);
        novoLocal.setCapacidade(capacidadeLocal);


        Local localSalvo = service.salvarLocal(novoLocal);


        System.out.println("\n--- Local Cadastrado com Sucesso ---");
        System.out.println("Detalhes: " + localSalvo);
        System.out.println("ID Gerado: " + localSalvo.getId_local());

        scanner.close();
        System.out.println("\nCadastro concluído!");
    }
}