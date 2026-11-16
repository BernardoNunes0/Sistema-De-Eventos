package com.evento;

/** Representa um ingresso vinculado a um evento e a um titular. */
public final class Ingresso {

    private final Evento evento;
    private final Participante titular;

    /**
     * Cria um ingresso para o evento e o titular informados.
     *
     * @param codigo código informado para o ingresso
     * @param evento evento vinculado ao ingresso
     * @param titular participante titular do ingresso
     */
    public Ingresso(String codigo, Evento evento, Participante titular) {
        this.evento = evento;
        this.titular = titular;
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
