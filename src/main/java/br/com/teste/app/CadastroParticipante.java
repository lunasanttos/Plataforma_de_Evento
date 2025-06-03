package br.com.teste.app;

import br.com.teste.model.Participante;
import br.com.teste.service.ParticipanteService;
import java.util.Scanner;

public class CadastroParticipante {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParticipanteService service = new ParticipanteService();

        System.out.println("Cadastro de Participante ");


        System.out.print("Nome do Participante: ");
        String nomeParticipante = scanner.nextLine();


        System.out.print("Email do Participante: ");
        String emailParticipante = scanner.nextLine();


        System.out.print("CPF do Participante (apenas números): ");
        String cpfParticipante = scanner.nextLine();


        Participante novoParticipante = new Participante(
                0,
                nomeParticipante,
                emailParticipante,
                cpfParticipante
        );

        boolean participanteFoiSalvo = service.inserir(novoParticipante);


        if (participanteFoiSalvo) {
            System.out.println("\nParticipante Cadastrado com Sucesso");
            System.out.println("Detalhes:");
            System.out.println("Nome: " + novoParticipante.getNome());
            System.out.println("Email: " + novoParticipante.getEmail());
            System.out.println("CPF: " + novoParticipante.getCpf());
            System.out.println("ID (informado, não o gerado pelo BD): " + novoParticipante.getId_participante()); // Será 0
        } else {
            System.out.println("\nFalha ao cadastrar participante. Verifique os dados.");
        }

        scanner.close();
        System.out.println("\nCadastro concluído!");
    }
}