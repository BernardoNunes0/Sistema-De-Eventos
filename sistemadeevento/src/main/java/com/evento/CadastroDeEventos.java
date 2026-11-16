package com.evento;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/** Gerencia o cadastro e a busca de eventos pelo código. */
public class CadastroDeEventos {
    // Relaciona o código de cada evento ao objeto correspondente.
    private final Map<String, Evento> eventos = new HashMap<>();

    public void cadastrar(Evento evento) {
        // Impede que o novo evento substitua outro com o mesmo código.
        if (eventos.containsKey(evento.getCodigo())) {
            throw new IllegalArgumentException("Já existe um evento com esse código.");
        }

        // Guarda o evento no mapa usando o código como chave.
        eventos.put(evento.getCodigo(), evento);
    }

    /**
     * Busca um evento pelo código.
     * @param codigo código usado na busca
     * @return evento encontrado ou null se o código não estiver cadastrado
     */
    public Evento buscarPorCodigo(String codigo) {
        return eventos.get(codigo);
    }
    /**
     * Retorna uma lista com os eventos cadastrados.
     * @return cópia dos eventos armazenados
     */
    public List<Evento> listar() {
        return new ArrayList<>(eventos.values());
    }

    /**
     * Atualiza um evento que já está cadastrado.
     * @param evento evento com os dados atualizados
     */
    public void atualizar(Evento evento) {
        // Só atualiza se o código já estiver cadastrado.
        if (!eventos.containsKey(evento.getCodigo())) {
            throw new IllegalArgumentException("Evento não encontrado.");
        }

        eventos.put(evento.getCodigo(), evento);
    }
}
