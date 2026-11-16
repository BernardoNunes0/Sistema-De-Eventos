package com.evento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class CadastroDeEventosTest {

    @Test
    void deveExcluirEventoCadastrado() {
        CadastroDeEventos cadastro = new CadastroDeEventos();
        LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);

        Evento evento = new Evento(
            "EVT-6", "Festival", data, "Mineirão", BigDecimal.ZERO
        );

        cadastro.cadastrar(evento);
        cadastro.excluir("EVT-6");

        assertNull(cadastro.buscarPorCodigo("EVT-6"));
    }

    @Test
    void deveAtualizarEventoCadastrado() {
        CadastroDeEventos cadastro = new CadastroDeEventos();
        LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);

        Evento original = new Evento(
            "EVT-5", "Festival antigo", data, "Mineirão", BigDecimal.ZERO
        );
        Evento atualizado = new Evento(
            "EVT-5", "Festival novo", data, "Mineirão", BigDecimal.ZERO
        );

        cadastro.cadastrar(original);
        cadastro.atualizar(atualizado);

        assertSame(atualizado, cadastro.buscarPorCodigo("EVT-5"));
    }

    @Test
    void deveListarEventosCadastrados() {
        CadastroDeEventos cadastro = new CadastroDeEventos();
        LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);

        Evento primeiro = new Evento(
            "EVT-3", "Festival A", data, "Mineirão", BigDecimal.ZERO
        );
        Evento segundo = new Evento(
            "EVT-4", "Festival B", data, "Mineirão", BigDecimal.ZERO
        );

        cadastro.cadastrar(primeiro);
        cadastro.cadastrar(segundo);

        List<Evento> eventos = cadastro.listar();

        assertEquals(2, eventos.size());
        assertTrue(eventos.contains(primeiro));
        assertTrue(eventos.contains(segundo));
    }

    @Test
    void deveImpedirCadastroDeEventosComCodigoDuplicado() {
        CadastroDeEventos cadastro = new CadastroDeEventos();
        LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);

        Evento primeiro = new Evento(
            "EVT-2", "Festival", data, "Mineirão", new BigDecimal("120.00")
        );
        Evento duplicado = new Evento(
            "EVT-2", "Outro festival", data, "Mineirão", new BigDecimal("90.00")
        );

        cadastro.cadastrar(primeiro);

        assertThrows(
            IllegalArgumentException.class,
            () -> cadastro.cadastrar(duplicado)
        );
        assertSame(primeiro, cadastro.buscarPorCodigo("EVT-2"));
    }

    @Test
    void deveCadastrarEventoEEncontrarPeloCodigo() {
        CadastroDeEventos cadastro = new CadastroDeEventos();

        Evento evento = new Evento(
            "EVT-1",
            "Festival",
            LocalDateTime.of(2026, 12, 5, 20, 0),
            "Mineirão",
            new BigDecimal("120.00")
        );

        cadastro.cadastrar(evento);

        assertSame(evento, cadastro.buscarPorCodigo("EVT-1"));
    }
}
