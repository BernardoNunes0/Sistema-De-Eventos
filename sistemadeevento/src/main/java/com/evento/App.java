package com.evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        try (Scanner teclado = new Scanner(System.in)) {
            while (true) {
                System.out.println("Sistema de Eventos");
                System.out.println("1 - Eventos");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                System.out.flush();

                if (!teclado.hasNextLine() || "0".equals(teclado.nextLine().trim())) {
                    break;
                }

                System.out.println("Opção indisponível.");
            }
        }

        System.out.println("Sistema encerrado.");
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
