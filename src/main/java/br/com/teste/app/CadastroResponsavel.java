package br.com.teste.app;

import br.com.teste.model.Responsavel;
// SERVICE
import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroResponsavel {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // ResponsavelService service = new ResponsavelService(); Instancia o serviço

        System.out.println(" Cadastro de Responsável ");

        String nomeResponsavel = "";
        String emailResponsavel = "";


        System.out.print("Nome do Responsável: ");
        nomeResponsavel = scanner.nextLine();


        System.out.print("Email do Responsável: ");
        emailResponsavel = scanner.nextLine();


        Responsavel novoResponsavel = new Responsavel();
        novoResponsavel.setNome(nomeResponsavel);
        novoResponsavel.setEmail(emailResponsavel);

        //service
        //  Responsavel responsavelSalvo = service.salvarResponsavel(novoResponsavel);


        System.out.println("\n Responsável Cadastrado com Sucesso ");
       // System.out.println("Detalhes: " + responsavelSalvo);
       // System.out.println("ID: " + responsavelSalvo.getId_responsavel());

        scanner.close();
        System.out.println("\nCadastro concluído!");
    }
}