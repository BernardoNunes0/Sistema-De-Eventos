package com.evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Gerencia o cadastro de participantes. */
public final class CadastroDeParticipantes {

    /** Participantes identificados pelo código. */
    private final Map<String, Participante> participantes = new HashMap<>();

    /**
     * Cadastra um participante pelo código.
     *
     * @param participante participante a cadastrar
     * @throws IllegalArgumentException se o participante for nulo ou o código já existir
     */
    public void cadastrar(final Participante participante) {
        if (participante == null) {
            throw new IllegalArgumentException("O participante não pode ser nulo.");
        }

        if (participantes.putIfAbsent(participante.getCodigo(), participante) != null) {
            throw new IllegalArgumentException("Já existe um participante com esse código.");
        }
    }

    /**
     * Busca um participante pelo código.
     *
     * @param codigo código procurado
     * @return participante encontrado ou null se o código não estiver cadastrado
     */
    public Participante buscarPorCodigo(final String codigo) {
        return participantes.get(codigo);
    }

    /**
     * Lista os participantes cadastrados.
     *
     * @return cópia da lista de participantes
     */
    public List<Participante> listar() {
        return new ArrayList<>(participantes.values());
    }

}
