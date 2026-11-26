
package com.evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes da classe CadastroDeIngressos. */
class CadastroDeIngressosTest {

    private static final int ANO_EVENTO = 2026;
    private static final int MES_EVENTO = 12;
    private static final int DIA_EVENTO = 5;
    private static final int HORA_EVENTO = 20;

    @Test
    void deveEmitirIngressoValido() {
        Evento evento = new Evento(
                "E001", "Festival de Jazz",
                LocalDateTime.of(
                        ANO_EVENTO, MES_EVENTO, DIA_EVENTO, HORA_EVENTO, 0),
                "Mineirão", BigDecimal.ZERO);

        Participante titular = new Participante(
                "P001", "Ana", "ana@example.com");

        Ingresso ingresso = new Ingresso("I001", evento, titular);
        CadastroDeIngressos cadastro = new CadastroDeIngressos();

        cadastro.emitirIngresso(ingresso);

        assertSame(ingresso, cadastro.consultarIngresso("I001"));
    }

    @Test
    void deveRejeitarIngressoComCodigoDuplicado() {
        Evento evento = new Evento(
                "E001", "Festival de Jazz",
                LocalDateTime.of(
                        ANO_EVENTO, MES_EVENTO, DIA_EVENTO, HORA_EVENTO, 0),
                "Mineirão", BigDecimal.ZERO);

        Participante titular = new Participante(
                "P001", "Ana", "ana@example.com");

        Ingresso primeiro = new Ingresso("I001", evento, titular);
        Ingresso segundo = new Ingresso("I001", evento, titular);
        CadastroDeIngressos cadastro = new CadastroDeIngressos();

        cadastro.emitirIngresso(primeiro);

        assertThrows(IllegalArgumentException.class,
                () -> cadastro.emitirIngresso(segundo));
    }

    @Test
    void deveRejeitarIngressoNulo() {
        CadastroDeIngressos cadastro = new CadastroDeIngressos();

        assertThrows(NullPointerException.class,
                () -> cadastro.emitirIngresso(null));
    }

    @Test
    void deveRetornarNuloAoConsultarIngressoInexistente() {
        CadastroDeIngressos cadastro = new CadastroDeIngressos();

        assertNull(cadastro.consultarIngresso("I999"));
    }

    @Test
    void deveRejeitarConsultaComCodigoNulo() {
        CadastroDeIngressos cadastro = new CadastroDeIngressos();

        assertThrows(NullPointerException.class,
                () -> cadastro.consultarIngresso(null));
    }

    @Test
    void deveConsultarIngressoCadastrado() {
        Evento evento = new Evento(
                "E001", "Festival de Jazz",
                LocalDateTime.of(
                        ANO_EVENTO, MES_EVENTO, DIA_EVENTO, HORA_EVENTO, 0),
                "Mineirão", BigDecimal.ZERO);

        Participante titular = new Participante(
                "P001", "Ana", "ana@example.com");

        Ingresso ingresso = new Ingresso("I001", evento, titular);
        CadastroDeIngressos cadastro = new CadastroDeIngressos();

        cadastro.emitirIngresso(ingresso);

        assertSame(ingresso, cadastro.consultarIngresso("I001"));
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistemIngressos() {
        CadastroDeIngressos cadastro = new CadastroDeIngressos();

        List<Ingresso> ingressos = cadastro.listarIngressos();

        assertTrue(ingressos.isEmpty());
    }

    @Test
    void deveListarIngressoCadastrado() {
        Evento evento = new Evento(
                "E001", "Festival de Jazz",
                LocalDateTime.of(
                        ANO_EVENTO, MES_EVENTO, DIA_EVENTO, HORA_EVENTO, 0),
                "Mineirão", BigDecimal.ZERO);

        Participante titular = new Participante(
                "P001", "Ana", "ana@example.com");

        Ingresso ingresso = new Ingresso("I001", evento, titular);
        CadastroDeIngressos cadastro = new CadastroDeIngressos();

        cadastro.emitirIngresso(ingresso);

        List<Ingresso> ingressos = cadastro.listarIngressos();

        assertEquals(1, ingressos.size());
        assertTrue(ingressos.contains(ingresso));
    }
}
