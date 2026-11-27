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
        final CadastroDeParticipantes participantes = new CadastroDeParticipantes();
        final Participante participante = new Participante("P001", "Ana", "ana@example.com");

        participantes.cadastrar(participante);

        final Participante cadastradoParticipante = participantes.buscarPorCodigo("P001");

        System.out.println("Participante cadastrado: "
                + cadastradoParticipante.getCodigo() + " - " + cadastradoParticipante.getNome());
        final CadastroDeIngressos ingressos = new CadastroDeIngressos();
        final Ingresso ingresso = new Ingresso("I001", cadastrado, cadastradoParticipante);

        ingressos.emitirIngresso(ingresso);

        final Ingresso ingressoEmitido = ingressos.consultarIngresso("I001");

        System.out.println("Ingresso emitido: "
                + ingressoEmitido.getCodigo() + " - "
                + ingressoEmitido.getEvento().getNome() + " - "
                + ingressoEmitido.getTitular().getNome());
        cadastrado.alterarDados("Festival de Jazz BH",
                cadastrado.getData(), cadastrado.getLocal());
        eventos.atualizar(cadastrado);

        final Evento eventoAtualizado = eventos.buscarPorCodigo("E001");
        System.out.println("Evento atualizado: "
                + eventoAtualizado.getCodigo() + " - " + eventoAtualizado.getNome());

        participantes.atualizar("P001", "Ana Silva", "ana.silva@example.com");

        final Participante participanteAtualizado = participantes.buscarPorCodigo("P001");
        System.out.println("Participante atualizado: "
                + participanteAtualizado.getCodigo() + " - " + participanteAtualizado.getNome());

        final Participante bruno = new Participante("P002", "Bruno", "bruno@example.com");
        participantes.cadastrar(bruno);
        ingressos.alterarIngresso("I001", participantes.buscarPorCodigo("P002"));

        final Ingresso ingressoAtualizado = ingressos.consultarIngresso("I001");
        System.out.println("Titular atualizado: "
                + ingressoAtualizado.getCodigo() + " - "
                + ingressoAtualizado.getTitular().getNome());
    }
}
