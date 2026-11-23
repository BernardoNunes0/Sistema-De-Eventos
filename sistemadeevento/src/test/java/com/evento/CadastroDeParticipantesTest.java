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
    private final CadastroDeParticipantes cadastro = new CadastroDeParticipantes();

    /** Verifica que a busca devolve o participante cadastrado. */
    @Test
    void deveBuscarParticipanteCadastradoPeloCodigo() {
        final Participante ana = new Participante("001", "Ana", "ana@example.com");

        cadastro.cadastrar(ana);

        assertSame(ana, cadastro.buscarPorCodigo("001"));
    }

    /** Verifica que um código duplicado preserva o cadastro original. */
    @Test
    void deveImpedirCadastroDeCodigoDuplicado() {
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        final Participante bruno = new Participante("001", "Bruno", "bruno@example.com");

        cadastro.cadastrar(ana);

        assertThrows(IllegalArgumentException.class, () -> cadastro.cadastrar(bruno));
        assertSame(ana, cadastro.buscarPorCodigo("001"));
    }

    /** Verifica que o cadastro recusa um participante nulo. */
    @Test
    void deveRecusarParticipanteNulo() {

        assertThrows(IllegalArgumentException.class, () -> cadastro.cadastrar(null));
    }

    /** Verifica o cadastro de participantes com códigos diferentes. */
    @Test
    void deveCadastrarParticipantesComCodigosDiferentes() {
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        final Participante bruno = new Participante("002", "Bruno", "bruno@example.com");

        cadastro.cadastrar(ana);
        cadastro.cadastrar(bruno);

        assertSame(ana, cadastro.buscarPorCodigo("001"));
        assertSame(bruno, cadastro.buscarPorCodigo("002"));
    }

    @Test
    void deveRetornarNuloAoBuscarEmCadastroVazio() {

        assertNull(cadastro.buscarPorCodigo("001"));
    }

    @Test
    void deveRetornarNuloParaCodigoNaoCadastrado() {
        cadastro.cadastrar(new Participante("001", "Ana", "ana@example.com"));

        assertNull(cadastro.buscarPorCodigo("002"));
    }

    @Test
    void deveListarCadastroVazio() {

        assertTrue(cadastro.listar().isEmpty());
    }

    @Test
    void deveListarTodosOsParticipantes() {
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
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        cadastro.cadastrar(ana);

        cadastro.listar().clear();

        assertSame(ana, cadastro.buscarPorCodigo("001"));
        assertEquals(1, cadastro.listar().size());
    }

    @Test
    void deveAtualizarNomeEEmailDoParticipante() {
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        cadastro.cadastrar(ana);

        cadastro.atualizar("001", "Ana Silva", "ana.silva@example.com");

        assertSame(ana, cadastro.buscarPorCodigo("001"));
        assertEquals("Ana Silva", ana.getNome());
        assertEquals("ana.silva@example.com", ana.getEmail());
    }

    @Test
    void deveAceitarNomeComUmCaractereAoAtualizar() {
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        cadastro.cadastrar(ana);

        cadastro.atualizar("001", "A", "ana@example.com");

        assertEquals("A", ana.getNome());
    }

    @Test
    void deveRecusarAtualizacaoDeCodigoNaoCadastrado() {

        assertThrows(IllegalArgumentException.class,
                () -> cadastro.atualizar("999", "Ana", "ana@example.com"));

        assertTrue(cadastro.listar().isEmpty());
    }

    @Test
    void devePreservarDadosAoRecusarEmailInvalido() {
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        cadastro.cadastrar(ana);

        assertThrows(IllegalArgumentException.class,
                () -> cadastro.atualizar("001", "Ana Silva", "emailinvalido"));

        assertEquals("Ana", ana.getNome());
        assertEquals("ana@example.com", ana.getEmail());
    }

    @Test
    void deveExcluirParticipanteCadastrado() {
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        cadastro.cadastrar(ana);

        cadastro.excluir("001");

        assertNull(cadastro.buscarPorCodigo("001"));
        assertTrue(cadastro.listar().isEmpty());
    }

    @Test
    void devePreservarOutrosParticipantesAoExcluir() {
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        final Participante bruno = new Participante("002", "Bruno", "bruno@example.com");
        cadastro.cadastrar(ana);
        cadastro.cadastrar(bruno);

        cadastro.excluir("001");

        assertNull(cadastro.buscarPorCodigo("001"));
        assertSame(bruno, cadastro.buscarPorCodigo("002"));
        assertEquals(1, cadastro.listar().size());
    }

    @Test
    void deveRecusarExclusaoDeCodigoNaoCadastrado() {
        final Participante ana = new Participante("001", "Ana", "ana@example.com");
        cadastro.cadastrar(ana);

        assertThrows(IllegalArgumentException.class, () -> cadastro.excluir("999"));

        assertSame(ana, cadastro.buscarPorCodigo("001"));
    }

}
