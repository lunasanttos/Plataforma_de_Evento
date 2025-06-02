package br.com.teste.app;

import br.com.teste.model.Participante;
// Importa o serviço
import java.util.Scanner;

public class CadastroParticipante {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //  ParticipanteService service = new ParticipanteService(); Instancia o serviço

        System.out.println("Cadastro de Participante ");


        System.out.print("Nome do Participante: ");
        String nomeParticipante = scanner.nextLine();


        System.out.print("Email do Participante: ");
        String emailParticipante = scanner.nextLine();


        System.out.print("CPF do Participante (apenas números): ");
        String cpfParticipante = scanner.nextLine();


        Participante novoParticipante = new Participante();

        novoParticipante.setNome(nomeParticipante);
        novoParticipante.setEmail(emailParticipante);
        novoParticipante.setCpf(cpfParticipante);

        //service
        //Participante participanteSalvo = service.salvarParticipante(novoParticipante);


        System.out.println("\nParticipante Cadastrado com Sucesso");
        //System.out.println("Detalhes: " + participanteSalvo);
        //System.out.println("ID Gerado: " + participanteSalvo.getId_participante());

        scanner.close();
        System.out.println("\nCadastro concluído!");
    }
}