package com.evento;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes da demonstração do sistema no terminal. */
class AppTest {

    @Test
    void deveExibirEventoCadastradoNaDemonstracao() {
        final ByteArrayOutputStream saida = new ByteArrayOutputStream();
        final PrintStream saidaOriginal = System.out;

        try (PrintStream captura = new PrintStream(saida)) {
            System.setOut(captura);

            App.main(new String[0]);

            assertTrue(saida.toString().contains(
                    "Evento cadastrado: E001 - Festival de Jazz"));
        } finally {
            System.setOut(saidaOriginal);
        }
    }

    @Test
    void deveExibirParticipanteCadastradoNaDemonstracao() {
        final ByteArrayOutputStream saida = new ByteArrayOutputStream();
        final PrintStream saidaOriginal = System.out;

        try (PrintStream captura = new PrintStream(saida)) {
            System.setOut(captura);

            App.main(new String[0]);

            assertTrue(saida.toString().contains(
                    "Participante cadastrado: P001 - Ana"));
        } finally {
            System.setOut(saidaOriginal);
        }
    }

    @Test
    void deveExibirIngressoEmitidoNaDemonstracao() {
        final ByteArrayOutputStream saida = new ByteArrayOutputStream();
        final PrintStream saidaOriginal = System.out;

        try (PrintStream captura = new PrintStream(saida)) {
            System.setOut(captura);

            App.main(new String[0]);

            assertTrue(saida.toString().contains(
                    "Ingresso emitido: I001 - Festival de Jazz - Ana"));
        } finally {
            System.setOut(saidaOriginal);
        }
    }

    @Test
    void deveExibirDadosAtualizadosNaDemonstracao() {
        final ByteArrayOutputStream saida = new ByteArrayOutputStream();
        final PrintStream saidaOriginal = System.out;

        try (PrintStream captura = new PrintStream(saida)) {
            System.setOut(captura);

            App.main(new String[0]);

            final String texto = saida.toString();
            assertTrue(texto.contains("Evento atualizado: E001 - Festival de Jazz BH"));
            assertTrue(texto.contains("Participante atualizado: P001 - Ana Silva"));
            assertTrue(texto.contains("Titular atualizado: I001 - Bruno"));
        } finally {
            System.setOut(saidaOriginal);
        }
    }

    @Test
    void deveExibirExclusoesNaDemonstracao() {
        final ByteArrayOutputStream saida = new ByteArrayOutputStream();
        final PrintStream saidaOriginal = System.out;

        try (PrintStream captura = new PrintStream(saida)) {
            System.setOut(captura);

            App.main(new String[0]);

            final String texto = saida.toString();
            assertTrue(texto.contains("Ingresso excluído: I001"));
            assertTrue(texto.contains("Participante excluído: P001"));
            assertTrue(texto.contains("Evento excluído: E001"));
        } finally {
            System.setOut(saidaOriginal);
        }
    }
}
