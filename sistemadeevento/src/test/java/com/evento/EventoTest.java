package com.evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Testes da classe Evento. */
class EventoTest {
    @Test
    void deveRetornarNomeInformadoAoCriarEvento() {
        String nome = "Festival Neon Lights 2026";
        Evento evento = new Evento(
            "musica-01", nome, LocalDateTime.parse("2026-12-05T20:00"),
            "Mineirão",
            new  BigDecimal("120.00")
        );
        assertEquals(nome, evento.getNome());
    }

    @Test
    void deveManterNomeDoPrimeiroEventoAposCriarOutro() {
        LocalDateTime data = LocalDateTime.parse("2026-12-05T20:00");
        Evento primeiro = new Evento(
            "musica-01", "Festival Neon Lights 2026", data, "Mineirão", BigDecimal.ZERO
        );
        Evento segundo = new Evento(
            "musica-02", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO
        );
        assertEquals("Festival Neon Lights 2026", primeiro.getNome());
        assertEquals("Festival de Jazz", segundo.getNome());
    }

    @Test
    void deveRetornarNomeComUmCaractere() {
        Evento evento = new Evento(
            "musica-03", "A", LocalDateTime.parse("2026-12-05T20:00"),
            "Mineirão", BigDecimal.ZERO
        );
        assertEquals("A", evento.getNome());
    }

    @Test
    void deveRetornarCodigoInformadoAoCriarEvento() {
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights 2026",
            LocalDateTime.parse("2026-12-05T20:00"), "Mineirão", BigDecimal.ZERO
        );
        assertEquals("musica-01", evento.getCodigo());
    }

    @Test
    void deveManterCodigosDistintosParaEventosComMesmoNome() {
        LocalDateTime data = LocalDateTime.parse("2026-12-05T20:00");
        Evento primeiro = new Evento(
            "musica-01", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO
        );
        Evento segundo = new Evento(
            "musica-02", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO
        );
        assertEquals("musica-01", primeiro.getCodigo());
        assertEquals("musica-02", segundo.getCodigo());
    }

    @Test
    void devePreservarZerosAEsquerdaNoCodigo() {
        Evento evento = new Evento(
            "001", "Festival de Jazz", LocalDateTime.parse("2026-12-05T20:00"),
            "Mineirão", BigDecimal.ZERO
        );
        assertEquals("001", evento.getCodigo());
    }

    @Test
    void deveRetornarPrecoInformadoAoCriarEvento() {
        BigDecimal preco = new BigDecimal("120.00");
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights 2026",
            LocalDateTime.parse("2026-12-05T20:00"), "Mineirão", preco
        );
        assertEquals(0, preco.compareTo(evento.getPreco()));
    }

    @Test
    void deveRetornarPrecoZeroParaEventoGratuito() {
        Evento evento = new Evento(
            "musica-02", "Festival de Jazz", LocalDateTime.parse("2026-12-05T20:00"),
            "Mineirão", BigDecimal.ZERO
        );
        assertEquals(0, BigDecimal.ZERO.compareTo(evento.getPreco()));
    }

    @Test
    void deveRecusarPrecoNegativoAoCriarEvento() {
        BigDecimal precoNegativo = new BigDecimal("-0.01");
        LocalDateTime data = LocalDateTime.parse("2026-12-05T20:00");
        assertThrows(IllegalArgumentException.class, () -> new Evento(
            "musica-03", "Festival de Jazz", data, "Mineirão", precoNegativo
        ));
    }

    @Test
    void deveAtualizarPrecoDoEvento() {
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights 2026",
            LocalDateTime.parse("2026-12-05T20:00"), "Mineirão", new BigDecimal("120.00")
        );
        BigDecimal novoPreco = new BigDecimal("90.00");
        evento.alterarPreco(novoPreco);
        assertEquals(0, novoPreco.compareTo(evento.getPreco()));
    }

    @Test
    void devePermitirTornarEventoGratuito() {
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights 2026",
            LocalDateTime.parse("2026-12-05T20:00"), "Mineirão", new BigDecimal("120.00")
        );
        evento.alterarPreco(BigDecimal.ZERO);
        assertEquals(0, BigDecimal.ZERO.compareTo(evento.getPreco()));
    }

    @Test
    void deveManterPrecoOriginalAoRecusarAlteracaoNegativa() {
        BigDecimal precoOriginal = new BigDecimal("120.00");
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights 2026",
            LocalDateTime.parse("2026-12-05T20:00"), "Mineirão", precoOriginal
        );
        assertThrows(IllegalArgumentException.class,
            () -> evento.alterarPreco(new BigDecimal("-0.01")));
        assertEquals(0, precoOriginal.compareTo(evento.getPreco()));
    }

    @Test
    void deveRetornarLocalInformadoAoCriarEvento() {
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights 2026",
            LocalDateTime.parse("2026-12-05T20:00"), "Mineirão", new BigDecimal("120.00")
        );
        assertEquals("Mineirão", evento.getLocal());
    }

    @Test
    void deveManterLocalDeCadaEvento() {
        LocalDateTime data = LocalDateTime.parse("2026-12-05T20:00");
        Evento primeiro = new Evento(
            "musica-01", "Festival de Jazz", data, "Mineirão", BigDecimal.ZERO
        );
        Evento segundo = new Evento(
            "musica-02", "Festival de Jazz", data, "Palácio das Artes", BigDecimal.ZERO
        );
        assertEquals("Mineirão", primeiro.getLocal());
        assertEquals("Palácio das Artes", segundo.getLocal());
    }

    @Test
    void devePreservarNomeCompletoDoLocal() {
        String local = "Mineirão - Portão 3";
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights 2026",
            LocalDateTime.parse("2026-12-05T20:00"), local, BigDecimal.ZERO
        );
        assertEquals(local, evento.getLocal());
    }

    @Test
    void deveRetornarDataEHorarioInformadosAoCriarEvento() {
        LocalDateTime data = LocalDateTime.parse("2026-12-05T20:30");
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights 2026", data, "Mineirão", BigDecimal.ZERO
        );
        assertEquals(data, evento.getData());
    }

    @Test
    void devePreservarDataDoEventoAMeiaNoite() {
        LocalDateTime meiaNoite = LocalDateTime.parse("2027-01-01T00:00");
        Evento evento = new Evento(
            "musica-01", "Show da Virada", meiaNoite, "Mineirão", BigDecimal.ZERO
        );
        assertEquals(meiaNoite, evento.getData());
    }

    @Test
    void deveManterHorarioDeCadaEventoNoMesmoDia() {
        LocalDateTime inicio = LocalDateTime.parse("2027-01-10T18:00");
        LocalDateTime encerramento = LocalDateTime.parse("2027-01-10T21:00");

        Evento abertura = new Evento(
            "musica-01", "Show de abertura", inicio, "Mineirão", BigDecimal.ZERO
        );
        Evento festival = new Evento(
            "musica-02", "Show de encerramento", encerramento, "Mineirão", BigDecimal.ZERO
        );

        assertEquals(inicio, abertura.getData());
        assertEquals(encerramento, festival.getData());
    }

    @Test
    void deveAlterarNomeDataELocalDoEvento() {
        LocalDateTime dataOriginal = LocalDateTime.parse("2026-12-05T20:00");
        LocalDateTime novaData = LocalDateTime.parse("2026-12-12T18:30");
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights", dataOriginal, "Mineirão", BigDecimal.ZERO
        );

        evento.alterarDados("Festival de Jazz", novaData, "Palácio das Artes");

        assertEquals("Festival de Jazz", evento.getNome());
        assertEquals(novaData, evento.getData());
        assertEquals("Palácio das Artes", evento.getLocal());
    }

    @Test
    void devePreservarCodigoEPrecoAoAlterarDados() {
        BigDecimal precoOriginal = new BigDecimal("120.00");
        LocalDateTime dataOriginal = LocalDateTime.parse("2026-12-05T20:00");
        LocalDateTime novaData = LocalDateTime.parse("2026-12-12T18:30");
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights", dataOriginal, "Mineirão", precoOriginal
        );

        evento.alterarDados("Festival de Jazz", novaData, "Palácio das Artes");

        assertEquals("musica-01", evento.getCodigo());
        assertEquals(0, precoOriginal.compareTo(evento.getPreco()));
    }

    @Test
    void devePreservarDadosAoRecusarDataNula() {
        LocalDateTime dataOriginal = LocalDateTime.parse("2026-12-05T20:00");
        Evento evento = new Evento(
            "musica-01", "Festival Neon Lights", dataOriginal, "Mineirão", BigDecimal.ZERO
        );

        assertThrows(IllegalArgumentException.class,
            () -> evento.alterarDados("Festival de Jazz", null, "Palácio das Artes"));

        assertEquals("Festival Neon Lights", evento.getNome());
        assertEquals(dataOriginal, evento.getData());
        assertEquals("Mineirão", evento.getLocal());
    }
}
