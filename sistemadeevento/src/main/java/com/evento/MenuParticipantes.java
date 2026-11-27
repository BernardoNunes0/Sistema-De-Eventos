package com.evento;

import java.util.Scanner;

/** Executa as operações do cadastro de participantes pelo terminal. */
final class MenuParticipantes {

    private MenuParticipantes() {
    }

    /**
     * Exibe o menu de participantes até o usuário escolher voltar.
     *
     * @param teclado leitor da entrada do terminal
     * @param participantes cadastro usado nas operações do menu
     */
    static void executar(Scanner teclado, CadastroDeParticipantes participantes) {
        while (true) {
            System.out.println("Participantes");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            final String opcao = App.lerLinha(teclado, "Escolha uma opção: ");
            if ("0".equals(opcao)) {
                return;
            }

            executarOperacao(teclado, participantes, opcao);
        }
    }

    private static void executarOperacao(Scanner teclado, CadastroDeParticipantes participantes,
            String opcao) {
        try {
            if ("1".equals(opcao)) {
                cadastrar(teclado, participantes);
            } else if ("2".equals(opcao)) {
                consultar(teclado, participantes);
            } else if ("3".equals(opcao)) {
                alterar(teclado, participantes);
            } else if ("4".equals(opcao)) {
                excluir(teclado, participantes);
            } else {
                System.out.println("Opção indisponível.");
            }
        } catch (IllegalArgumentException erro) {
            System.out.println("Não foi possível concluir a operação. " + erro.getMessage());
        }
    }

    private static void cadastrar(Scanner teclado, CadastroDeParticipantes participantes) {
        final String codigo = App.lerLinha(teclado, "Código do participante: ");
        final String nome = App.lerLinha(teclado, "Nome do participante: ");
        final String email = App.lerLinha(teclado, "E-mail do participante: ");

        participantes.cadastrar(new Participante(codigo, nome, email));

        System.out.println("Participante cadastrado: " + codigo + " - " + nome);
    }

    private static void consultar(Scanner teclado, CadastroDeParticipantes participantes) {
        final String codigo = App.lerLinha(teclado, "Código do participante: ");
        final Participante participante = participantes.buscarPorCodigo(codigo);

        if (participante == null) {
            System.out.println("Participante não encontrado.");
            return;
        }

        System.out.println("Participante encontrado: "
                + participante.getCodigo() + " - " + participante.getNome());
    }

    private static void alterar(Scanner teclado, CadastroDeParticipantes participantes) {
        final String codigo = App.lerLinha(teclado, "Código do participante: ");
        final String nome = App.lerLinha(teclado, "Novo nome: ");
        final String email = App.lerLinha(teclado, "Novo e-mail: ");

        participantes.atualizar(codigo, nome, email);

        System.out.println("Participante atualizado: " + codigo + " - " + nome);
    }

    private static void excluir(Scanner teclado, CadastroDeParticipantes participantes) {
        final String codigo = App.lerLinha(teclado, "Código do participante: ");

        participantes.excluir(codigo);

        System.out.println("Participante excluído: " + codigo);
    }
}
