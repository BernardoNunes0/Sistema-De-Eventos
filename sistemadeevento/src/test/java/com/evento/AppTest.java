package com.evento;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes da demonstração do sistema no terminal. */
class AppTest {

    @Test
    void deveExibirEventoCadastradoNaDemonstracao() {
        final String texto = executarDemonstracao();
        assertTrue(texto.contains("Evento cadastrado: E001 - Festival de Jazz"));
    }

    @Test
    void deveExibirParticipanteCadastradoNaDemonstracao() {
        final String texto = executarDemonstracao();
        assertTrue(texto.contains("Participante cadastrado: P001 - Ana"));
    }

    @Test
    void deveExibirIngressoEmitidoNaDemonstracao() {
        final String texto = executarDemonstracao();
        assertTrue(texto.contains("Ingresso emitido: I001 - Festival de Jazz - Ana"));
    }

    @Test
    void deveExibirDadosAtualizadosNaDemonstracao() {
        final String texto = executarDemonstracao();
        assertTrue(texto.contains("Evento atualizado: E001 - Festival de Jazz BH"));
        assertTrue(texto.contains("Participante atualizado: P001 - Ana Silva"));
        assertTrue(texto.contains("Titular atualizado: I001 - Bruno"));
    }

    @Test
    void deveExibirExclusoesNaDemonstracao() {
        final String texto = executarDemonstracao();
        assertTrue(texto.contains("Ingresso excluído: I001"));
        assertTrue(texto.contains("Participante excluído: P001"));
        assertTrue(texto.contains("Evento excluído: E001"));
    }

    private static String executarDemonstracao() {
        final ByteArrayOutputStream saida = new ByteArrayOutputStream();
        final PrintStream saidaOriginal = System.out;

        try (PrintStream captura = new PrintStream(saida)) {
            System.setOut(captura);
            App.main(new String[0]);
            return saida.toString();
        } finally {
            System.setOut(saidaOriginal);
        }
    }
}
