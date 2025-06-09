package br.com.teste.app;

import br.com.teste.model.Inscricao;
import br.com.teste.model.Evento;
import br.com.teste.model.Participante;
import br.com.teste.service.InscricaoService;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CadastroInscricao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InscricaoService service = new InscricaoService();

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



        Evento eventoSelecionado = service.buscarEventoPorId(idEvento);
        Participante participanteSelecionado = service.buscarParticipantePorId(idParticipante);


        if (eventoSelecionado == null) {
            System.out.println("Erro: Evento com ID " + idEvento + " não encontrado. Cadastro de inscrição cancelado.");
            scanner.close();
            return;
        }
        if (participanteSelecionado == null) {
            System.out.println("Erro: Participante com ID " + idParticipante + " não encontrado. Cadastro de inscrição cancelado.");
            scanner.close();
            return;
        }


        LocalDate dataAtual = LocalDate.now();


        Inscricao novaInscricao = new Inscricao(
                0,
                eventoSelecionado,
                participanteSelecionado,
                dataAtual
        );


        boolean inscricaoSalva = service.inserir(novaInscricao);


        if (inscricaoSalva) {
            System.out.println("\nInscrição Cadastrada com Sucesso!");
            System.out.println("Detalhes:");
            System.out.println("Evento (ID): " + novaInscricao.getEvento().getId_evento());
            System.out.println("Participante (ID): " + novaInscricao.getParticipante().getId_participante());
            System.out.println("Data da Inscrição: " + novaInscricao.getDataInscricao());
        } else {
            System.out.println("\nFalha ao cadastrar inscrição. Verifique os dados.");
        }


        scanner.close();
        System.out.println("\nCadastro de inscrição concluído!");
    }
}