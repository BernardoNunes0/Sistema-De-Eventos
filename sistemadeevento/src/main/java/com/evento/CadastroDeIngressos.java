
package com.evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        validarCodigoDuplicado(codigo);

        ingressos.put(codigo, ingresso);
    }

    /**
     * Consulta um ingresso pelo seu codigo.
     *
     * @param codigo codigo do ingresso
     * @return ingresso encontrado ou null se nao existir
     * @throws NullPointerException se o codigo for nulo
     */
    public Ingresso consultarIngresso(String codigo) {
        Objects.requireNonNull(codigo,
                "O código não pode ser nulo.");

        return ingressos.get(codigo);
    }

    /**
     * Lista todos os ingressos cadastrados no sistema.
     *
     * @return lista dos ingressos cadastrados
     */
    public List<Ingresso> listarIngressos() {
        return new ArrayList<>(ingressos.values());
    }

    /**
     * Altera o titular de um ingresso cadastrado.
     *
     * @param codigo codigo do ingresso
     * @param novoTitular novo titular do ingresso
     * @throws IllegalArgumentException se o ingresso nao existir
     */
    public void alterarIngresso(String codigo, Participante novoTitular) {
        Ingresso ingresso = consultarIngresso(codigo);

        if (ingresso == null) {
            throw new IllegalArgumentException(
                    "Ingresso nao encontrado.");
        }

        ingresso.alterarTitular(novoTitular);
    }

    /**
     * Exclui um ingresso cadastrado pelo codigo.
     *
     * @param codigo codigo do ingresso a excluir
     * @throws IllegalArgumentException se o ingresso nao existir
     */
    public void excluirIngresso(String codigo) {
        Ingresso ingresso = ingressos.remove(codigo);

        if (ingresso == null) {
            throw new IllegalArgumentException(
                    "Ingresso nao encontrado.");
        }
    }

    /**
     * Verifica se o código do ingresso já está cadastrado.
     *
     * @param codigo código do ingresso
     */
    private void validarCodigoDuplicado(String codigo) {
        if (ingressos.containsKey(codigo)) {
            throw new IllegalArgumentException(
                    "Já existe um ingresso com esse código.");
        }
    }

}
