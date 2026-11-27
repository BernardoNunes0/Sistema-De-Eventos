package com.evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Executa a demonstração do sistema no terminal. */
public final class App {

    private App() {
    }

    /**
     * Inicia a demonstração do sistema.
     *
     * @param args argumentos da execução
     */
    public static void main(String[] args) {
        final CadastroDeEventos eventos = new CadastroDeEventos();
        final Evento evento = new Evento(
                "E001",
                "Festival de Jazz",
                LocalDateTime.of(2026, 12, 5, 20, 0),
                "Mineirão",
                BigDecimal.ZERO);

        eventos.cadastrar(evento);

        final Evento cadastrado = eventos.buscarPorCodigo("E001");

        System.out.println("Evento cadastrado: "
                + cadastrado.getCodigo() + " - " + cadastrado.getNome());
    }
}
