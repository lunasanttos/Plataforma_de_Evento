package br.com.teste.app;

import br.com.teste.service.GerarCertificado;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroCertificado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerarCertificado gerador = new GerarCertificado();

        System.out.println("GERADOR DE CERTIFICADO");

        int idInscricao = 0;
        boolean idInscricaoValido = false;

        while (!idInscricaoValido) {
            try {
                System.out.print("Digite o ID da Inscrição para gerar o certificado: ");
                idInscricao = scanner.nextInt();
                if (idInscricao <= 0) {
                    System.out.println("ID da Inscrição inválido. Deve ser um número positivo.");
                } else {
                    idInscricaoValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                scanner.next();
            }
        }
        scanner.nextLine();


        boolean sucesso = gerador.gerarEInserirCertificado(idInscricao);

        if (sucesso) {
            System.out.println("\nCertificado processado com sucesso!");
        } else {
            System.out.println("\nFalha na geração do certificado.");
        }

        scanner.close();
        System.out.println("\nProcesso de geração de certificado concluído!");
    }
}