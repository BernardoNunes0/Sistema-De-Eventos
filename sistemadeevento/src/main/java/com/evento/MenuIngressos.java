package com.evento;

import java.util.Scanner;

/** Executa as operações do cadastro de ingressos pelo terminal. */
final class MenuIngressos {

    private MenuIngressos() {
    }

    /**
     * Exibe o menu de ingressos até o usuário escolher voltar.
     *
     * @param teclado leitor da entrada do terminal
     * @param ingressos cadastro de ingressos da sessão
     * @param eventos cadastro de eventos da sessão
     * @param participantes cadastro de participantes da sessão
     */
    static void executar(Scanner teclado, CadastroDeIngressos ingressos,
            CadastroDeEventos eventos, CadastroDeParticipantes participantes) {
        while (true) {
            System.out.println("Ingressos");
            System.out.println("1 - Emitir");
            System.out.println("2 - Consultar");
            System.out.println("3 - Alterar titular");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            final String opcao = App.lerLinha(teclado, "Escolha uma opção: ");
            if ("0".equals(opcao)) {
                return;
            }

            executarOperacao(teclado, opcao, ingressos, eventos, participantes);
        }
    }

    private static void executarOperacao(Scanner teclado, String opcao,
            CadastroDeIngressos ingressos, CadastroDeEventos eventos,
            CadastroDeParticipantes participantes) {
        try {
            if ("1".equals(opcao)) {
                emitir(teclado, ingressos, eventos, participantes);
            } else if ("2".equals(opcao)) {
                consultar(teclado, ingressos);
            } else if ("3".equals(opcao)) {
                alterarTitular(teclado, ingressos, participantes);
            } else if ("4".equals(opcao)) {
                excluir(teclado, ingressos);
            } else {
                System.out.println("Opção indisponível.");
            }
        } catch (IllegalArgumentException | IllegalStateException erro) {
            System.out.println("Não foi possível concluir a operação. " + erro.getMessage());
        }
    }

    private static void emitir(Scanner teclado, CadastroDeIngressos ingressos,
            CadastroDeEventos eventos, CadastroDeParticipantes participantes) {
        final String codigo = App.lerLinha(teclado, "Código do ingresso: ");
        final String codigoEvento = App.lerLinha(teclado, "Código do evento: ");
        final String codigoTitular = App.lerLinha(teclado, "Código do titular: ");

        final Evento evento = eventos.buscarPorCodigo(codigoEvento);
        if (evento == null) {
            throw new IllegalArgumentException("Evento não encontrado.");
        }

        final Participante titular = participantes.buscarPorCodigo(codigoTitular);
        if (titular == null) {
            throw new IllegalArgumentException("Participante não encontrado.");
        }

        final Ingresso ingresso = new Ingresso(codigo, evento, titular);
        ingressos.emitirIngresso(ingresso);

        exibirIngresso("Ingresso emitido: ", ingresso);
    }

    private static void consultar(Scanner teclado, CadastroDeIngressos ingressos) {
        final String codigo = App.lerLinha(teclado, "Código do ingresso: ");
        final Ingresso ingresso = ingressos.consultarIngresso(codigo);

        if (ingresso == null) {
            System.out.println("Ingresso não encontrado.");
            return;
        }

        exibirIngresso("Ingresso encontrado: ", ingresso);
    }

    private static void alterarTitular(Scanner teclado, CadastroDeIngressos ingressos,
            CadastroDeParticipantes participantes) {
        final String codigo = App.lerLinha(teclado, "Código do ingresso: ");
        final String codigoTitular = App.lerLinha(teclado, "Código do novo titular: ");

        final Participante novoTitular = participantes.buscarPorCodigo(codigoTitular);
        if (novoTitular == null) {
            throw new IllegalArgumentException("Participante não encontrado.");
        }

        ingressos.alterarIngresso(codigo, novoTitular);

        System.out.println("Titular atualizado: " + codigo + " - " + novoTitular.getNome());
    }

    private static void excluir(Scanner teclado, CadastroDeIngressos ingressos) {
        final String codigo = App.lerLinha(teclado, "Código do ingresso: ");

        ingressos.excluirIngresso(codigo);

        System.out.println("Ingresso excluído: " + codigo);
    }

    private static void exibirIngresso(String mensagem, Ingresso ingresso) {
        System.out.println(mensagem + ingresso.getCodigo() + " - "
                + ingresso.getEvento().getNome() + " - " + ingresso.getTitular().getNome());
    }
}
