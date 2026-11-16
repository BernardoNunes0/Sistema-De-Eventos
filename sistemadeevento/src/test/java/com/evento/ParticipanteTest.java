package com.evento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testes da classe Participante.
 */
class ParticipanteTest {

    /**
     * Verifica a criacao de um participante com dados validos.
     */
    @Test
    void deveCriarParticipanteComDadosValidos() {
        assertDoesNotThrow(() -> new Participante("P001", "Ana", "ana@email.com"));
    }

    /**
     * Verifica a rejeicao de identificador nulo.
     */
    @Test
    void deveRejeitarIdentificadorNulo() {
        assertThrows(NullPointerException.class,
                () -> new Participante(null, "Ana", "ana@email.com"));
    }

    /**
     * Verifica a rejeicao de nome nulo.
     */
    @Test
    void deveRejeitarNomeNulo() {
        assertThrows(NullPointerException.class,
                () -> new Participante("P001", null, "ana@email.com"));
    }

    /**
     * Verifica a rejeicao de email nulo.
     */
    @Test
    void deveRejeitarEmailNulo() {
        assertThrows(NullPointerException.class,
                () -> new Participante("P001", "Ana", null));
    }

    /**
     * Verifica a rejeicao de identificador vazio.
     */
    @Test
    void deveRejeitarIdentificadorVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Participante("", "Ana", "ana@email.com"));
    }

    /**
     * Verifica a rejeicao de nome vazio.
     */
    @Test
    void deveRejeitarNomeVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Participante("P001", "", "ana@email.com"));
    }

    /**
     * Verifica a rejeicao de email vazio.
     */
    @Test
    void deveRejeitarEmailVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Participante("P001", "Ana", ""));
    }

    /**
     * Verifica a rejeicao de identificador contendo apenas espacos.
     */
    @Test
    void deveRejeitarIdentificadorComApenasEspacos() {
        assertThrows(IllegalArgumentException.class,
                () -> new Participante("   ", "Ana", "ana@email.com"));
    }

    /**
     * Verifica a rejeicao de nome contendo apenas espacos.
     */
    @Test
    void deveRejeitarNomeComApenasEspacos() {
        assertThrows(IllegalArgumentException.class,
                () -> new Participante("P001", "   ", "ana@email.com"));
    }

    /**
     * Verifica a rejeicao de email contendo apenas espacos.
     */
    @Test
    void deveRejeitarEmailComApenasEspacos() {
        assertThrows(IllegalArgumentException.class,
                () -> new Participante("P001", "Ana", "   "));
    }

    /**
     * Verifica o retorno do codigo informado no construtor.
     */
    @Test
    void deveRetornarCodigoInformado() {
        final Participante participante =
                new Participante("P123", "Ana", "ana@email.com");
        assertEquals("P123", participante.getCodigo());
    }

    /**
     * Verifica a preservacao dos zeros no codigo.
     */
    @Test
    void devePreservarZerosDoCodigo() {
        final Participante participante =
                new Participante("P0001", "Ana", "ana@email.com");

        assertEquals("P0001", participante.getCodigo());
    }
}
