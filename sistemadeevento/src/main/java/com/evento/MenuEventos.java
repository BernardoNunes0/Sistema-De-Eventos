package com.evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/** Executa as operações do cadastro de eventos pelo terminal. */
final class MenuEventos {

    private MenuEventos() {
    }

    /**
     * Exibe o menu de eventos até o usuário escolher voltar.
     *
     * @param teclado leitor da entrada do terminal
     * @param eventos cadastro usado nas operações do menu
     */
    static void executar(Scanner teclado, CadastroDeEventos eventos) {
        while (true) {
            System.out.println("Eventos");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            final String opcao = App.lerLinha(teclado, "Escolha uma opção: ");
            if ("0".equals(opcao)) {
                return;
            }

            executarOperacao(teclado, eventos, opcao);
        }
    }

    private static void executarOperacao(Scanner teclado, CadastroDeEventos eventos,
            String opcao) {
        try {
            if ("1".equals(opcao)) {
                cadastrarEvento(teclado, eventos);
            } else if ("2".equals(opcao)) {
                consultarEvento(teclado, eventos);
            } else if ("3".equals(opcao)) {
                alterarEvento(teclado, eventos);
            } else if ("4".equals(opcao)) {
                excluirEvento(teclado, eventos);
            } else {
                System.out.println("Opção indisponível.");
            }
        } catch (DateTimeParseException | IllegalArgumentException erro) {
            System.out.println("Não foi possível concluir a operação. " + erro.getMessage());
        }
    }

    private static void cadastrarEvento(Scanner teclado, CadastroDeEventos eventos) {
        final String codigo = App.lerLinha(teclado, "Código do evento: ");
        final Evento evento = lerEvento(teclado, codigo);

        eventos.cadastrar(evento);

        System.out.println("Evento cadastrado: " + evento.getCodigo() + " - " + evento.getNome());
    }

    private static void consultarEvento(Scanner teclado, CadastroDeEventos eventos) {
        final String codigo = App.lerLinha(teclado, "Código do evento: ");
        final Evento evento = eventos.buscarPorCodigo(codigo);

        if (evento == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        System.out.println("Evento encontrado: " + evento.getCodigo() + " - " + evento.getNome());
    }

    private static void alterarEvento(Scanner teclado, CadastroDeEventos eventos) {
        final String codigo = App.lerLinha(teclado, "Código do evento: ");
        final Evento evento = eventos.buscarPorCodigo(codigo);

        if (evento == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        final Evento novosDados = lerEvento(teclado, codigo);
        evento.alterarPreco(novosDados.getPreco());
        evento.alterarDados(novosDados.getNome(), novosDados.getData(), novosDados.getLocal());
        eventos.atualizar(evento);

        System.out.println("Evento atualizado: " + evento.getCodigo() + " - " + evento.getNome());
    }

    private static void excluirEvento(Scanner teclado, CadastroDeEventos eventos) {
        final String codigo = App.lerLinha(teclado, "Código do evento: ");

        eventos.excluir(codigo);

        System.out.println("Evento excluído: " + codigo);
    }

    private static Evento lerEvento(Scanner teclado, String codigo) {
        final String nome = App.lerLinha(teclado, "Nome do evento: ");
        final String dataDigitada = App.lerLinha(teclado, "Data e hora (AAAA-MM-DD HH:MM): ");
        final LocalDateTime data = LocalDateTime.parse(dataDigitada.replace(' ', 'T'));
        final String local = App.lerLinha(teclado, "Local do evento: ");
        final String precoDigitado = App.lerLinha(teclado, "Preço do evento: ");
        final BigDecimal preco = new BigDecimal(precoDigitado.replace(',', '.'));

        return new Evento(codigo, nome, data, local, preco);
    }
}
