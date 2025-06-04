package br.com.teste.app;

import br.com.teste.model.Evento;
import br.com.teste.model.Local;
import br.com.teste.model.Responsavel;
import br.com.teste.service.EventoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CadastroEvento {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventoService service = new EventoService();

        System.out.println("Cadastro do evento");

        System.out.print("Nome do Evento: ");
        String nomeEvento = scanner.nextLine();

        System.out.print("Tipo do Evento: ");
        String tipoEvento = scanner.nextLine();

        LocalDate dataEvento = null;
        boolean dataValida = false;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (!dataValida) {
            try {
                System.out.print("Data do Evento: ");
                String dataStr = scanner.nextLine();
                dataEvento = LocalDate.parse(dataStr, dateFormatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido.");
            }
        }

        LocalTime horaEvento = null;
        boolean horaValida = false;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        while (!horaValida) {
            try {
                System.out.print("Horário do Evento: ");
                String horaStr = scanner.nextLine();
                horaEvento = LocalTime.parse(horaStr, timeFormatter);
                horaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Entrada inválida.");
            }
        }

        System.out.print("Descrição do Evento: ");
        String descricaoEvento = scanner.nextLine();

        int idLocal = 0;
        boolean localIdValido = false;
        while (!localIdValido) {
            try {
                System.out.print("ID do Local: ");
                idLocal = scanner.nextInt();
                if (idLocal <= 0) {
                    System.out.println("ID do Local inválido");
                } else {
                    localIdValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida.");
                scanner.next();
            }
        }
        scanner.nextLine();

        System.out.print("Quantidade de responsáveis para o evento: ");
        int qtdResponsaveis = 0;
        try {
            qtdResponsaveis = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Responsáveis não serão adicionados.");
            scanner.nextLine();
        }
        scanner.nextLine();

        List<Responsavel> responsaveis = new ArrayList<>();
        for (int i = 0; i < qtdResponsaveis; i++) {
            System.out.println("Dados do responsável " + (i + 1));

            int idResp = 0;
            System.out.print("ID do responsável: ");
            try {
                idResp = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("ID inválido. Pulando este responsável.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            System.out.print("Nome do responsável: ");
            String nomeResp = scanner.nextLine();

            System.out.print("Email do responsável: ");
            String emailResp = scanner.nextLine();

            Responsavel resp = new Responsavel(idResp, nomeResp, emailResp);
            responsaveis.add(resp);
        }

        Local localSelecionado = new Local(idLocal);

        Evento novoEvento = new Evento(
                0,
                nomeEvento,
                tipoEvento,
                dataEvento,
                horaEvento,
                descricaoEvento,
                localSelecionado
        );


        novoEvento.setResponsavelLista(responsaveis);

        boolean eventoSalvo = service.inserir(novoEvento);

        if (eventoSalvo) {
            System.out.println("\nEvento cadastrado com sucesso!");
            System.out.println("Detalhes:");
            System.out.println("Nome: " + novoEvento.getNome());
            System.out.println("Tipo: " + novoEvento.getTipo());
            System.out.println("Data: " + novoEvento.getData().format(dateFormatter));
            System.out.println("Hora: " + novoEvento.getHora().format(timeFormatter));
            System.out.println("Descrição: " + novoEvento.getDescricao());
            System.out.println("Local (ID): " + novoEvento.getId_Local().getId_local());
            System.out.println("Responsáveis:");
            for (Responsavel r : responsaveis) {
                System.out.println("  ID: " + r.getId_responsavel() + ", Nome: " + r.getNome() + ", Email: " + r.getEmail());
            }
        } else {
            System.out.println("\nFalha ao cadastrar evento. Verifique os dados inseridos.");
        }

        scanner.close();
        System.out.println("\nCadastro de evento concluído!");
    }
}
