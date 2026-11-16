package com.evento;

import java.util.Objects;

/**
 * Representa um participante do evento.
 */
public class Participante {

    /**
     * Cria um participante com dados obrigatorios.
     *
     * @param codigo identificador do participante
     * @param nome nome do participante
     * @param email email do participante
     * @throws IllegalArgumentException se algum argumento for vazio
     * @throws NullPointerException se algum argumento for nulo
     */
    public Participante(final String codigo, final String nome, final String email) {
        Objects.requireNonNull(codigo, "O codigo nao pode ser nulo.");
        Objects.requireNonNull(nome, "O nome nao pode ser nulo.");
        Objects.requireNonNull(email, "O email nao pode ser nulo.");

        if (codigo.isEmpty()) {
            throw new IllegalArgumentException("O codigo nao pode ser vazio.");
        }

        if (nome.isEmpty()) {
            throw new IllegalArgumentException("O nome nao pode ser vazio.");
        }

        if (email.isEmpty()) {
            throw new IllegalArgumentException("O email nao pode ser vazio.");
        }
    }
}
