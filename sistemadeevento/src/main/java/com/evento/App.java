package com.evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Executa a demonstração do sistema no terminal. */
public final class App {

    private App() {
    }

    /**
     * Inicia o sistema no terminal.
     *
     * @param args argumentos da execução
     */
    public static void main(String[] args) {
        if (args.length > 0 && "--demo".equals(args[0])) {
            executarDemonstracao();
            return;
        }

        executarMenu();
    }

    private static void executarMenu() {
        final CadastroDeEventos eventos = new CadastroDeEventos();

        try (Scanner teclado = new Scanner(System.in)) {
            while (true) {
                System.out.println("Sistema de Eventos");
                System.out.println("1 - Eventos");
                System.out.println("0 - Sair");

                final String opcao = lerLinha(teclado, "Escolha uma opção: ");
                if ("0".equals(opcao)) {
                    break;
                }
                if ("1".equals(opcao)) {
                    executarMenuEventos(teclado, eventos);
                } else {
                    System.out.println("Opção indisponível.");
                }
            }
        } catch (NoSuchElementException fimDaEntrada) {
            System.out.println("Entrada encerrada.");
        }

        System.out.println("Sistema encerrado.");
    }

    private static void executarMenuEventos(Scanner teclado, CadastroDeEventos eventos) {
        while (true) {
            System.out.println("Eventos");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar");
            System.out.println("0 - Voltar");

            final String opcao = lerLinha(teclado, "Escolha uma opção: ");
            if ("0".equals(opcao)) {
                return;
            }
            if ("1".equals(opcao)) {
                cadastrarEvento(teclado, eventos);
            } else if ("2".equals(opcao)) {
                consultarEvento(teclado, eventos);
            } else {
                System.out.println("Opção indisponível.");
            }
        }
    }

    private static void cadastrarEvento(Scanner teclado, CadastroDeEventos eventos) {
        try {
            final String codigo = lerLinha(teclado, "Código do evento: ");
            final String nome = lerLinha(teclado, "Nome do evento: ");
            final String dataDigitada = lerLinha(teclado, "Data e hora (AAAA-MM-DD HH:MM): ");
            final LocalDateTime data = LocalDateTime.parse(dataDigitada.replace(' ', 'T'));
            final String local = lerLinha(teclado, "Local do evento: ");
            final String precoDigitado = lerLinha(teclado, "Preço do evento: ");
            final BigDecimal preco = new BigDecimal(precoDigitado.replace(',', '.'));

            final Evento evento = new Evento(codigo, nome, data, local, preco);
            eventos.cadastrar(evento);

            System.out.println("Evento cadastrado: " + evento.getCodigo() + " - " + evento.getNome());
        } catch (DateTimeParseException | IllegalArgumentException erro) {
            System.out.println("Não foi possível cadastrar o evento. " + erro.getMessage());
        }
    }

    private static void consultarEvento(Scanner teclado, CadastroDeEventos eventos) {
        final String codigo = lerLinha(teclado, "Código do evento: ");
        final Evento evento = eventos.buscarPorCodigo(codigo);

        if (evento == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        System.out.println("Evento encontrado: " + evento.getCodigo() + " - " + evento.getNome());
    }

    private static String lerLinha(Scanner teclado, String mensagem) {
        System.out.print(mensagem);
        System.out.flush();
        return teclado.nextLine().trim();
    }

    private static void executarDemonstracao() {
        final CadastroDeEventos eventos = new CadastroDeEventos();
        final Evento evento = new Evento(
                "E001",
                "Festival de Jazz",
                LocalDateTime.of(2026, 12, 5, 20, 0),
                "Mineirão",
                BigDecimal.ZERO);

        eventos.cadastrar(evento);

        final Evento cadastrado = eventos.buscarPorCodigo("E001");

        System.out.println("Evento cadastrado: "
                + cadastrado.getCodigo() + " - " + cadastrado.getNome());
        final CadastroDeParticipantes participantes = new CadastroDeParticipantes();
        final Participante participante = new Participante("P001", "Ana", "ana@example.com");

        participantes.cadastrar(participante);

        final Participante cadastradoParticipante = participantes.buscarPorCodigo("P001");

        System.out.println("Participante cadastrado: "
                + cadastradoParticipante.getCodigo() + " - " + cadastradoParticipante.getNome());
        final CadastroDeIngressos ingressos = new CadastroDeIngressos();
        final Ingresso ingresso = new Ingresso("I001", cadastrado, cadastradoParticipante);

        ingressos.emitirIngresso(ingresso);

        final Ingresso ingressoEmitido = ingressos.consultarIngresso("I001");

        System.out.println("Ingresso emitido: "
                + ingressoEmitido.getCodigo() + " - "
                + ingressoEmitido.getEvento().getNome() + " - "
                + ingressoEmitido.getTitular().getNome());
        cadastrado.alterarDados("Festival de Jazz BH",
                cadastrado.getData(), cadastrado.getLocal());
        eventos.atualizar(cadastrado);

        final Evento eventoAtualizado = eventos.buscarPorCodigo("E001");
        System.out.println("Evento atualizado: "
                + eventoAtualizado.getCodigo() + " - " + eventoAtualizado.getNome());

        participantes.atualizar("P001", "Ana Silva", "ana.silva@example.com");

        final Participante participanteAtualizado = participantes.buscarPorCodigo("P001");
        System.out.println("Participante atualizado: "
                + participanteAtualizado.getCodigo() + " - " + participanteAtualizado.getNome());

        final Participante bruno = new Participante("P002", "Bruno", "bruno@example.com");
        participantes.cadastrar(bruno);
        ingressos.alterarIngresso("I001", participantes.buscarPorCodigo("P002"));

        final Ingresso ingressoAtualizado = ingressos.consultarIngresso("I001");
        System.out.println("Titular atualizado: "
                + ingressoAtualizado.getCodigo() + " - "
                + ingressoAtualizado.getTitular().getNome());
        ingressos.excluirIngresso("I001");
        if (ingressos.consultarIngresso("I001") == null) {
            System.out.println("Ingresso excluído: I001");
        }

        participantes.excluir("P001");
        if (participantes.buscarPorCodigo("P001") == null) {
            System.out.println("Participante excluído: P001");
        }

        eventos.excluir("E001");
        if (eventos.buscarPorCodigo("E001") == null) {
            System.out.println("Evento excluído: E001");
        }
    }
}
