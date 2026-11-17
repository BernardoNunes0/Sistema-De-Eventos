package com.evento;

import java.util.Objects;

/** Representa um ingresso vinculado a um evento e a um titular. */
public final class Ingresso {

    private final String codigo;
    private final Evento evento;
    private final Participante titular;

    /**
     * Cria um ingresso para o evento e o titular informados.
     *
     * @param codigo  código informado para o ingresso
     * @param evento  evento vinculado ao ingresso
     * @param titular participante titular do ingresso
     * @throws IllegalArgumentException se o código for vazio ou contiver apenas
     *                                  espaços
     * @throws NullPointerException     se o código ou o evento for nulo
     */
    public Ingresso(String codigo, Evento evento, Participante titular) {
        Objects.requireNonNull(codigo, "O código do ingresso não pode ser nulo.");
        if (codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O código do ingresso não pode ser vazio ou conter apenas espaços.");
        }

        this.codigo = codigo;
        this.evento = Objects.requireNonNull(evento, "O evento do ingresso não pode ser nulo.");
        this.titular = titular;
    }

    /**
     * Retorna o código do ingresso.
     *
     * @return código informado na criação
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna o evento vinculado ao ingresso.
     *
     * @return evento do ingresso
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Retorna o titular do ingresso.
     *
     * @return participante titular
     */
    public Participante getTitular() {
        return titular;
    }
}
