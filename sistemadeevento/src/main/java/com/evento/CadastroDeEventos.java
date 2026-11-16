package com.evento;

import java.util.HashMap;
import java.util.Map;

public class CadastroDeEventos {

    private final Map<String, Evento> eventos = new HashMap<>();

    public void cadastrar(Evento evento) {
        eventos.put(evento.getCodigo(), evento);
    }

    public Evento buscarPorCodigo(String codigo) {
        return eventos.get(codigo);
    }
}
