package com.evento;

import java.util.Objects;

/**
 * Representa um participante do evento.
 */
public class Participante {

    /**
     * Codigo do participante.
     */
    private final String codigo;

    /**
     * Nome do participante.
     */
    private String nome;

    /**
     * Email do participante.
     */
    private String email;

    /**
     * Cria um participante com dados obrigatorios.
     *
     * @param codigo identificador do participante
     * @param nome nome do participante
     * @param email email do participante
     * @throws IllegalArgumentException se algum argumento for vazio ou contiver apenas espacos
     * @throws NullPointerException se algum argumento for nulo
     */
    public Participante(final String codigo, final String nome, final String email) {
        Objects.requireNonNull(codigo, "O codigo nao pode ser nulo.");
        Objects.requireNonNull(nome, "O nome nao pode ser nulo.");
        Objects.requireNonNull(email, "O email nao pode ser nulo.");

        if (codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O codigo nao pode ser vazio ou conter apenas espacos.");
        }

        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O nome nao pode ser vazio ou conter apenas espacos.");
        }

        if (email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O email nao pode ser vazio ou conter apenas espacos.");
        }

        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    /**
     * Retorna o codigo do participante.
     *
     * @return codigo do participante
     */
    public final String getCodigo() {
        return this.codigo;
    }

    /**
     * Retorna o nome do participante.
     *
     * @return nome do participante
     */
    public final String getNome() {
        return this.nome;
    }

    /**
     * Retorna o email do participante.
     *
     * @return email do participante
     */
    public final String getEmail() {
        return this.email;
    }

    /**
     * Atualiza o nome e o email do participante.
     *
     * @param novoNome novo nome do participante
     * @param novoEmail novo email do participante
     */
    public final void atualizarDados(final String novoNome, final String novoEmail) {
        this.nome = novoNome;
        this.email = novoEmail;
    }
}
