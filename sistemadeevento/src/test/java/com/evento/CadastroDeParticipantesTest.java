package com.evento;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void deveListarCadastroVazio() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();

        assertTrue(cadastro.listar().isEmpty());
    }

    @Test
    void deveListarTodosOsParticipantes() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        final Participante bruno = new Participante("002", "Bruno", "bruno@example.com");

        cadastro.cadastrar(ana);
        cadastro.cadastrar(bruno);

        final List<Participante> lista = cadastro.listar();

        assertEquals(2, lista.size());
        assertTrue(lista.contains(ana));
        assertTrue(lista.contains(bruno));
    }

    @Test
    void devePreservarCadastroAoLimparListaRetornada() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        cadastro.cadastrar(ana);

        cadastro.listar().clear();

        assertSame(ana, cadastro.buscarPorCodigo("001"));
        assertEquals(1, cadastro.listar().size());
    }

    @Test
    void deveAtualizarNomeEEmailDoParticipante() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        cadastro.cadastrar(ana);

        cadastro.atualizar("001", "Ana Silva", "ana.silva@example.com");

        assertSame(ana, cadastro.buscarPorCodigo("001"));
        assertEquals("Ana Silva", ana.getNome());
        assertEquals("ana.silva@example.com", ana.getEmail());
    }

    @Test
    void deveAceitarNomeComUmCaractereAoAtualizar() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        cadastro.cadastrar(ana);

        cadastro.atualizar("001", "A", "ana@example.com");

        assertEquals("A", ana.getNome());
    }

    @Test
    void deveRecusarAtualizacaoDeCodigoNaoCadastrado() {
        final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();

        assertThrows(IllegalArgumentException.class,
                () -> cadastro.atualizar("999", "Ana", "ana@example.com"));

        assertTrue(cadastro.listar().isEmpty());
    }

}
