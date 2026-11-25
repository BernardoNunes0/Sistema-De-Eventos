
package com.evento;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Gerencia o cadastro de ingressos do sistema de eventos. */
public class CadastroDeIngressos {

    private final Map<String, Ingresso> ingressos = new HashMap<>();

    /**
     * Emite e cadastra um ingresso no sistema.
     *
     * @param ingresso ingresso que será cadastrado
     * @throws NullPointerException se o ingresso for nulo
     * @throws IllegalArgumentException se o código já estiver cadastrado
     */

    public void emitirIngresso(Ingresso ingresso) {
        Objects.requireNonNull(ingresso,
                "O ingresso não pode ser nulo.");

        String codigo = ingresso.getCodigo();

        if (ingressos.containsKey(codigo)) {
            throw new IllegalArgumentException(
                    "Já existe um ingresso com esse código.");
        }

        ingressos.put(codigo, ingresso);
    }

    public Ingresso consultarIngresso(String codigo) {
        return ingressos.get(codigo);
    }
}
