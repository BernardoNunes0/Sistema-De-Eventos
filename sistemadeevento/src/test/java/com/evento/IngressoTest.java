package com.evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Testes da classe Ingresso. */
class IngressoTest {

    @Test
    void deveVincularEventoETitularAoCriarIngresso() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");

        final Ingresso ingresso = new Ingresso("I001", evento, titular);

        assertSame(evento, ingresso.getEvento());
        assertSame(titular, ingresso.getTitular());
    }

    @Test
    void deveRetornarCodigoInformadoAoCriarIngresso() {
        final String codigo = "I002";
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");

        final Ingresso ingresso = new Ingresso(codigo, evento, titular);

        assertEquals(codigo, ingresso.getCodigo());
    }

    @Test
    void deveRejeitarCodigoNuloAoCriarIngresso() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");

        assertThrows(NullPointerException.class, () -> new Ingresso(null, evento, titular));
    }

    @Test
    void deveRejeitarCodigoEmBrancoAoCriarIngresso() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");

        assertThrows(IllegalArgumentException.class, () -> new Ingresso("   ", evento, titular));
    }

    @Test
    void deveRejeitarEventoNuloAoCriarIngresso() {
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");

        assertThrows(NullPointerException.class, () -> new Ingresso("I001", null, titular));
    }

    @Test
    void deveRejeitarTitularNuloAoCriarIngresso() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);

        assertThrows(NullPointerException.class, () -> new Ingresso("I001", evento, null));
    }

    @Test
    void deveIniciarComoEmitido() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");

        final Ingresso ingresso = new Ingresso("I001", evento, titular);

        assertEquals(SituacaoIngresso.EMITIDO, ingresso.getSituacao());
    }

    @Test
    void deveMarcarIngressoEmitidoComoUtilizado() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");
        final Ingresso ingresso = new Ingresso("I001", evento, titular);

        ingresso.marcarComoUtilizado();

        assertEquals(SituacaoIngresso.UTILIZADO, ingresso.getSituacao());
    }

    @Test
    void deveRejeitarSegundoUsoDoIngresso() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");
        final Ingresso ingresso = new Ingresso("I001", evento, titular);

        ingresso.marcarComoUtilizado();

        assertThrows(IllegalStateException.class, () -> ingresso.marcarComoUtilizado());
    }

    @Test
    void deveCancelarIngressoEmitido() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");
        final Ingresso ingresso = new Ingresso("I001", evento, titular);

        ingresso.cancelar();

        assertEquals(SituacaoIngresso.CANCELADO, ingresso.getSituacao());
    }

    @Test
    void deveRejeitarCancelamentoDeIngressoUtilizado() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");
        final Ingresso ingresso = new Ingresso("I001", evento, titular);

        ingresso.marcarComoUtilizado();

        assertThrows(IllegalStateException.class, () -> ingresso.cancelar());
        assertEquals(SituacaoIngresso.UTILIZADO, ingresso.getSituacao());
    }

    @Test
    void deveRejeitarSegundoCancelamentoDoIngresso() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");
        final Ingresso ingresso = new Ingresso("I001", evento, titular);

        ingresso.cancelar();

        assertThrows(IllegalStateException.class, () -> ingresso.cancelar());
        assertEquals(SituacaoIngresso.CANCELADO, ingresso.getSituacao());
    }

    @Test
    void deveRejeitarUsoDeIngressoCancelado() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");
        final Ingresso ingresso = new Ingresso("I001", evento, titular);

        ingresso.cancelar();

        assertThrows(IllegalStateException.class, () -> ingresso.marcarComoUtilizado());
        assertEquals(SituacaoIngresso.CANCELADO, ingresso.getSituacao());
    }

    @Test
    void deveAlterarTitularDeIngressoEmitido() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titularOriginal = new Participante("P001", "Ana", "ana@example.com");
        final Participante novoTitular = new Participante("P002", "Bruno", "bruno@example.com");
        final Ingresso ingresso = new Ingresso("I001", evento, titularOriginal);

        ingresso.alterarTitular(novoTitular);

        assertSame(novoTitular, ingresso.getTitular());
    }

    @Test
    void deveRejeitarTrocaParaTitularNulo() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titularOriginal = new Participante("P001", "Ana", "ana@example.com");
        final Ingresso ingresso = new Ingresso("I001", evento, titularOriginal);

        assertThrows(NullPointerException.class, () -> ingresso.alterarTitular(null));
        assertSame(titularOriginal, ingresso.getTitular());
    }

    @Test
    void deveRejeitarTrocaDeTitularDeIngressoUtilizado() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento = new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titularOriginal = new Participante("P001", "Ana", "ana@example.com");
        final Participante novoTitular = new Participante("P002", "Bruno", "bruno@example.com");
        final Ingresso ingresso = new Ingresso("I001", evento, titularOriginal);

        ingresso.marcarComoUtilizado();

        assertThrows(IllegalStateException.class, () -> ingresso.alterarTitular(novoTitular));
        assertSame(titularOriginal, ingresso.getTitular());
    }
}
