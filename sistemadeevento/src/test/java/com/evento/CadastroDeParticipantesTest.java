package com.evento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/** Testes do cadastro de participantes. */
class CadastroDeParticipantesTest {

    /** Verifica que a busca devolve o participante cadastrado. */
    @Test
    void deveBuscarParticipanteCadastradoPeloCodigo() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();
        final Participante ana = new Participante("001", "Ana", "ana@example.com");

        cadastro.cadastrar(ana);

        assertSame(ana, cadastro.buscarPorCodigo("001"));
    }
}
