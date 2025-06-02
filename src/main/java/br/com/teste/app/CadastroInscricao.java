package br.com.teste.app;

import br.com.teste.model.Inscricao;
import br.com.teste.model.Evento;
import br.com.teste.model.Participante;
// Importa o serviço
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroInscricao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //InscricaoService service = new InscricaoService();  //Instancia o serviço

        System.out.println("Cadastro de Inscrição");

        int idEvento = 0;
        boolean eventoIdValido = false;
        while (!eventoIdValido) {
            try {
                System.out.print("ID do Evento: ");
                idEvento = scanner.nextInt();
                if (idEvento <= 0) {
                    System.out.println("Entrada inválida.");
                } else {
                    eventoIdValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida.");
                scanner.next();
            }
        }
        scanner.nextLine();

        int idParticipante = 0;
        boolean participanteIdValido = false;
        while (!participanteIdValido) {
            try {
                System.out.print("ID do Participante: ");
                idParticipante = scanner.nextInt();
                if (idParticipante <= 0) {
                    System.out.println("Entrada inválida.");
                } else {
                    participanteIdValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida.");
                scanner.next();
            }
        }
        scanner.nextLine();


        // SERVICE
       // Evento eventoSelecionado = service.buscarEventoPorId(idEvento);
       // Participante participanteSelecionado = service.buscarParticipantePorId(idParticipante);


        LocalDate dataAtual = LocalDate.now();

        Inscricao novaInscricao = new Inscricao();
       // novaInscricao.setEvento(eventoSelecionado);
       // novaInscricao.setParticipante(participanteSelecionado);
        novaInscricao.setDataInscricao(dataAtual);


       // Inscricao inscricaoSalva = service.salvarInscricao(novaInscricao);// SERVICE


        System.out.println("\nInscrição Cadastrada com Sucesso");
        //System.out.println("Detalhes: " + inscricaoSalva);
        //System.out.println("ID Gerado: " + inscricaoSalva.getId_inscricao());
       // System.out.println("Evento(ID): " + inscricaoSalva.getEvento().getId_evento());
       // System.out.println("Participante(ID): " + inscricaoSalva.getParticipante().getId_participante());


        scanner.close();
        System.out.println("\nCadastro de inscrição concluído!");
    }
}