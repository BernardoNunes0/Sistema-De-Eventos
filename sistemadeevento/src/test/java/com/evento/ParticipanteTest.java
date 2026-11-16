package com.evento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
}
