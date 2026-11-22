package com.evento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    /** Verifica que um código duplicado preserva o cadastro original. */
    @Test
    void deveImpedirCadastroDeCodigoDuplicado() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        final Participante bruno = new Participante("001", "Bruno", "bruno@example.com");

        cadastro.cadastrar(ana);

        assertThrows(IllegalArgumentException.class, () -> cadastro.cadastrar(bruno));
        assertSame(ana, cadastro.buscarPorCodigo("001"));
    }

    /** Verifica que o cadastro recusa um participante nulo. */
    @Test
    void deveRecusarParticipanteNulo() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();

        assertThrows(IllegalArgumentException.class, () -> cadastro.cadastrar(null));
    }

}
