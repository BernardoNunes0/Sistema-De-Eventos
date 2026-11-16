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

    /**
     * Verifica a independencia dos codigos entre participantes.
     */
    @Test
    void deveManterCodigosIndependentesEntreParticipantes() {
        final Participante primeiro =
                new Participante("P001", "Ana", "ana@email.com");
        final Participante segundo =
                new Participante("P002", "Bruno", "bruno@email.com");

        assertEquals("P001", primeiro.getCodigo());
        assertEquals("P002", segundo.getCodigo());
    }

    /**
     * Verifica o retorno do nome informado no construtor.
     */
    @Test
    void deveRetornarNomeInformado() {
        final Participante participante =
                new Participante("P001", "Ana", "ana@email.com");

        assertEquals("Ana", participante.getNome());
    }

    /**
     * Verifica a preservacao de nome composto e acentuado.
     */
    @Test
    void devePreservarNomeCompostoEAcentuado() {
        final Participante participante =
                new Participante("P001", "Ana Júlia", "ana@email.com");

        assertEquals("Ana Júlia", participante.getNome());
    }

    /**
     * Verifica a independencia dos nomes entre participantes.
     */
    @Test
    void deveManterNomesIndependentesEntreParticipantes() {
        final Participante primeiro =
                new Participante("P001", "Ana", "ana@email.com");
        final Participante segundo =
                new Participante("P002", "Bruno", "bruno@email.com");

        assertEquals("Ana", primeiro.getNome());
        assertEquals("Bruno", segundo.getNome());
    }

    /**
     * Verifica o retorno do email informado no construtor.
     */
    @Test
    void deveRetornarEmailInformado() {
        final Participante participante =
                new Participante("P001", "Ana", "ana@email.com");
        assertEquals("ana@email.com", participante.getEmail());
    }

    /**
     * Verifica a preservacao de email com ponto e subdominio.
     */
    @Test
    void devePreservarEmailComPontoESubdominio() {
        final Participante participante =
                new Participante("P001", "Ana", "ana.silva@alunos.exemplo.com");

        assertEquals("ana.silva@alunos.exemplo.com", participante.getEmail());
    }

    /**
     * Verifica a independencia dos emails entre participantes.
     */
    @Test
    void deveManterEmailsIndependentesEntreParticipantes() {
        final Participante primeiro =
                new Participante("P001", "Ana", "ana@email.com");
        final Participante segundo =
                new Participante("P002", "Bruno", "bruno@email.com");
        assertEquals("ana@email.com", primeiro.getEmail());
        assertEquals("bruno@email.com", segundo.getEmail());
    }

    /**
     * Verifica a atualizacao de nome e email com dados validos.
     */
    @Test
    void deveAtualizarNomeEEmailComDadosValidos() {
        final Participante participante =
                new Participante("P001", "Ana", "ana@email.com");
        participante.atualizarDados("Ana Maria", "novo@email.com");
        assertEquals("Ana Maria", participante.getNome());
        assertEquals("novo@email.com", participante.getEmail());
    }

    /**
     * Verifica a rejeicao de atualizacao com nome nulo.
     */
    @Test
    void deveRejeitarAtualizacaoComNomeNulo() {
        final Participante participante =
                new Participante("P001", "Ana", "ana@email.com");

        assertThrows(NullPointerException.class,
                () -> participante.atualizarDados(null, "novo@email.com"));

        assertEquals("P001", participante.getCodigo());
        assertEquals("Ana", participante.getNome());
        assertEquals("ana@email.com", participante.getEmail());
    }

    /**
     * Verifica a rejeicao de atualizacao com email nulo.
     */
    @Test
    void deveRejeitarAtualizacaoComEmailNulo() {
        final Participante participante =
                new Participante("P001", "Ana", "ana@email.com");

        assertThrows(NullPointerException.class,
                () -> participante.atualizarDados("Ana Maria", null));

        assertEquals("P001", participante.getCodigo());
        assertEquals("Ana", participante.getNome());
        assertEquals("ana@email.com", participante.getEmail());
    }

    /**
     * Verifica a rejeicao de atualizacao com nome vazio.
     */
    @Test
    void deveRejeitarAtualizacaoComNomeVazio() {
        final Participante participante =
                new Participante("P001", "Ana", "ana@email.com");

        assertThrows(IllegalArgumentException.class,
                () -> participante.atualizarDados("", "novo@email.com"));

        assertEquals("P001", participante.getCodigo());
        assertEquals("Ana", participante.getNome());
        assertEquals("ana@email.com", participante.getEmail());
    }

    /**
     * Verifica a rejeicao de atualizacao com email vazio.
     */
    @Test
    void deveRejeitarAtualizacaoComEmailVazio() {
        final Participante participante =
                new Participante("P001", "Ana", "ana@email.com");

        assertThrows(IllegalArgumentException.class,
                () -> participante.atualizarDados("Ana Maria", ""));

        assertEquals("P001", participante.getCodigo());
        assertEquals("Ana", participante.getNome());
        assertEquals("ana@email.com", participante.getEmail());
    }

    /**
     * Verifica a rejeicao de atualizacao com nome contendo apenas espacos.
     */
    @Test
    void deveRejeitarAtualizacaoComNomeComApenasEspacos() {
        final Participante participante =
                new Participante("P001", "Ana", "ana@email.com");

        assertThrows(IllegalArgumentException.class,
                () -> participante.atualizarDados("   ", "novo@email.com"));

        assertEquals("P001", participante.getCodigo());
        assertEquals("Ana", participante.getNome());
        assertEquals("ana@email.com", participante.getEmail());
    }

    /**
     * Verifica a rejeicao de atualizacao com email contendo apenas espacos.
     */
    @Test
    void deveRejeitarAtualizacaoComEmailComApenasEspacos() {
        final Participante participante =
                new Participante("P001", "Ana", "ana@email.com");

        assertThrows(IllegalArgumentException.class,
                () -> participante.atualizarDados("Ana Maria", "   "));

        assertEquals("P001", participante.getCodigo());
        assertEquals("Ana", participante.getNome());
        assertEquals("ana@email.com", participante.getEmail());
    }
}
