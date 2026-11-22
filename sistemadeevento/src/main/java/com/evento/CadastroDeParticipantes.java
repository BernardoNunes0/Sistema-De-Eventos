package com.evento;

import java.util.HashMap;
import java.util.Map;

/** Gerencia o cadastro de participantes. */
public final class CadastroDeParticipantes {

    /** Participantes identificados pelo código. */
    private final Map<String, Participante> participantes = new HashMap<>();

    /**
     * Cadastra um participante pelo código.
     *
     * @param participante participante a cadastrar
     */
    public void cadastrar(final Participante participante) {
        participantes.put(participante.getCodigo(), participante);
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
}
