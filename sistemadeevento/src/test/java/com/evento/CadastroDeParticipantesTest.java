package com.evento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
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

    /** Verifica o cadastro de participantes com códigos diferentes. */
    @Test
    void deveCadastrarParticipantesComCodigosDiferentes() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        final Participante bruno = new Participante("002", "Bruno", "bruno@example.com");

        cadastro.cadastrar(ana);
        cadastro.cadastrar(bruno);

        assertSame(ana, cadastro.buscarPorCodigo("001"));
        assertSame(bruno, cadastro.buscarPorCodigo("002"));
    }

    @Test
    void deveRetornarNuloAoBuscarEmCadastroVazio() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();

        assertNull(cadastro.buscarPorCodigo("001"));
    }

    @Test
    void deveRetornarNuloParaCodigoNaoCadastrado() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();
        cadastro.cadastrar(new Participante("001", "Ana", "ana@example.com"));

        assertNull(cadastro.buscarPorCodigo("002"));
    }

}
