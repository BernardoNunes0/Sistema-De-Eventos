package com.evento;

import java.util.HashMap;
import java.util.Map;

public class CadastroDeEventos {
    private final Map<String, Evento> eventos = new HashMap<>();

    public void cadastrar(Evento evento) {
        // Impede que o novo evento substitua outro com o mesmo código.
        if (eventos.containsKey(evento.getCodigo())) {
            throw new IllegalArgumentException("Já existe um evento com esse código.");
        }

        // Guarda o evento no mapa usando o código como chave.
        eventos.put(evento.getCodigo(), evento);
    }

    public Evento buscarPorCodigo(String codigo) {
        return eventos.get(codigo);
    }
}
