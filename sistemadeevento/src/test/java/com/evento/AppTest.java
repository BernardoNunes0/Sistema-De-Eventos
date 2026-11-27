package com.evento;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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

        assertTrue(texto.contains("Evento atualizado: E001 - Festival de Jazz BH"), texto);
        assertTrue(texto.contains("Participante atualizado: P001 - Ana Silva"), texto);
        assertTrue(texto.contains("Titular atualizado: I001 - Bruno"), texto);
    }

    @Test
    void deveExibirExclusoesNaDemonstracao() {
        final String texto = executarDemonstracao();
        assertTrue(texto.contains("Ingresso excluído: I001"));
        assertTrue(texto.contains("Participante excluído: P001"));
        assertTrue(texto.contains("Evento excluído: E001"));
    }

    @Test
    void deveAbrirMenuEEncerrarAoDigitarZero() {
        final String texto = executarApp("0\n");

        assertTrue(texto.contains("1 - Eventos"), texto);
        assertTrue(texto.contains("Escolha uma opção"), texto);
        assertTrue(texto.contains("Sistema encerrado."), texto);
    }

    @Test
    void deveCadastrarEConsultarEventoPeloMenu() {
        final String entrada = "1\n1\n"
                + "E002\nFeira de Tecnologia\n2026-12-10 19:30\n"
                + "Centro de Convencoes\n25.50\n"
                + "2\nE002\n0\n0\n";

        final String texto = executarApp(entrada);

        assertTrue(texto.contains("Evento cadastrado: E002 - Feira de Tecnologia"), texto);
        assertTrue(texto.contains("Evento encontrado: E002 - Feira de Tecnologia"), texto);
    }

    @Test
    void deveAlterarEExcluirEventoPeloMenu() {
        final String entrada = "1\n1\n"
                + "E002\nFeira de Tecnologia\n2026-12-10 19:30\n"
                + "Centro de Convencoes\n25.50\n"
                + "3\nE002\nFeira de Tecnologia BH\n2026-12-11 20:00\n"
                + "Sala Principal\n30.00\n"
                + "2\nE002\n4\nE002\n2\nE002\n0\n0\n";

        final String texto = executarApp(entrada);

        assertTrue(texto.contains("Evento encontrado: E002 - Feira de Tecnologia BH"), texto);
        assertTrue(texto.contains("Evento excluído: E002"), texto);
        assertTrue(texto.contains("Evento não encontrado."), texto);
    }

    private static String executarDemonstracao() {
        return executarApp("", "--demo");
    }

    private static String executarApp(final String entrada, final String... argumentos) {
        final ByteArrayOutputStream saida = new ByteArrayOutputStream();
        final InputStream entradaOriginal = System.in;
        final PrintStream saidaOriginal = System.out;

        try (PrintStream captura = new PrintStream(saida)) {
            System.setIn(new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)));
            System.setOut(captura);

            App.main(argumentos);

            return saida.toString();
        } finally {
            System.setIn(entradaOriginal);
            System.setOut(saidaOriginal);
        }
    }
}
