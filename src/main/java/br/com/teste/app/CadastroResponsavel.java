package br.com.teste.app;

import br.com.teste.model.Responsavel;
import br.com.teste.service.ResponsavelService;

import java.util.Scanner;

public class CadastroResponsavel {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResponsavelService service = new ResponsavelService();

        System.out.println(" Cadastro de Responsável ");

        String nomeResponsavel = "";
        String emailResponsavel = "";


        System.out.print("Nome do Responsável: ");
        nomeResponsavel = scanner.nextLine();


        System.out.print("Email do Responsável: ");
        emailResponsavel = scanner.nextLine();

        Responsavel novoResponsavel = new Responsavel(
                0,
                nomeResponsavel,
                emailResponsavel
        );


        boolean responsavelFoiSalvo = service.inserir(novoResponsavel);

        if (responsavelFoiSalvo) {
            System.out.println("\n Responsável Cadastrado com Sucesso ");
            System.out.println("Detalhes:");
            System.out.println("Nome: " + novoResponsavel.getNome());
            System.out.println("Email: " + novoResponsavel.getEmail());
            System.out.println("ID (informado, não o gerado pelo BD): " + novoResponsavel.getId_responsavel()); // Será 0
        } else {
            System.out.println("\nFalha ao cadastrar responsável. Verifique os dados.");
        }

        scanner.close();
        System.out.println("\nCadastro concluído!");
    }
}