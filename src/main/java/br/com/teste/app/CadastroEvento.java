package br.com.teste.app;

import br.com.teste.model.Evento;
import br.com.teste.model.Local;
import br.com.teste.service.EventoService;
import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
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
                System.out.println("formato de data invalido.");
            }
        }

        LocalTime horaEvento = null;
        boolean horaValida = false;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        while (!horaValida) {
            try {
                System.out.print("Horario do Evento:");
                String horaStr = scanner.nextLine();
                horaEvento = LocalTime.parse(horaStr, timeFormatter);
                horaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Entrada Inválida.");
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
                    System.out.println("ID do Local invalido");
                } else {
                    localIdValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida.");
                scanner.next();
            }
        }
        scanner.nextLine();

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
        boolean eventoSalvo = service.inserir(novoEvento);

        if (eventoSalvo) { // Verifica o retorno booleano do método inserir
            System.out.println("\nEvento Cadastrado com Sucesso!");
            System.out.println("Detalhes:");
            System.out.println("Nome: " + novoEvento.getNome());
            System.out.println("Tipo: " + novoEvento.getTipo());
            System.out.println("Data: " + novoEvento.getData().format(dateFormatter));
            System.out.println("Hora: " + novoEvento.getHora().format(timeFormatter));
            System.out.println("Descrição: " + novoEvento.getDescricao());
            System.out.println("Local (ID): " + novoEvento.getId_Local().getId_local()); // Acessa o ID do Local
            // Observação: O ID real gerado pelo banco de dados não está sendo atualizado no objeto `novoEvento`
            // após a inserção nesta estrutura. Para isso, o DAO precisaria retornar o ID ou atualizar o objeto.
        } else {
            System.out.println("\nFalha ao cadastrar evento. Verifique os dados inseridos.");
        }

        scanner.close();
        System.out.println("\nCadastro de evento concluído!");
    }
}