package com.evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class CadastroDeEventosTest {

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
