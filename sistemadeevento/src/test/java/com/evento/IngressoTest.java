package com.evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/** Testes da classe Ingresso. */
class IngressoTest {

    @Test
    void deveVincularEventoETitularAoCriarIngresso() {
        final LocalDateTime data = LocalDateTime.of(2026, 12, 5, 20, 0);
        final Evento evento =
                new Evento("E001", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO);
        final Participante titular = new Participante("P001", "Ana", "ana@example.com");

        final Ingresso ingresso = new Ingresso("I001", evento, titular);

        assertSame(evento, ingresso.getEvento());
        assertSame(titular, ingresso.getTitular());
    }
}
