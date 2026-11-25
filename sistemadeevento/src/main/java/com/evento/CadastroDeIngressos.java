
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
     * @param ingresso ingresso que sera cadastrado
     * @throws NullPointerException se o ingresso for nulo
     * @throws IllegalArgumentException se o codigo ja estiver cadastrado
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
     * @return lista contendo os ingressos cadastrados
     */
    public List<Ingresso> listarIngressos() {
        return new ArrayList<>(ingressos.values());
    }

    /**
     * Altera o titular de um ingresso cadastrado.
     *
     * @param codigo código do ingresso
     * @param novoTitular participante que será o novo titular
     * @throws NullPointerException se o código for nulo
     * @throws IllegalArgumentException se o ingresso não estiver cadastrado
     * @throws IllegalStateException se o ingresso não permitir alteração
     */
    public void alterarIngresso(String codigo, Participante novoTitular) {
        Ingresso ingresso = buscarIngressoObrigatorio(codigo);

        ingresso.alterarTitular(novoTitular);
    }

    /**
     * Exclui um ingresso cadastrado pelo seu código.
     *
     * @param codigo código do ingresso que será excluído
     * @throws NullPointerException se o código for nulo
     * @throws IllegalArgumentException se o ingresso não estiver cadastrado
     */
    public void excluirIngresso(String codigo) {
        buscarIngressoObrigatorio(codigo);

        ingressos.remove(codigo);
    }

    /**
     * Busca um ingresso e verifica se ele esta cadastrado.
     *
     * @param codigo codigo do ingresso
     * @return ingresso encontrado
     * @throws NullPointerException se o codigo for nulo
     * @throws IllegalArgumentException se o ingresso nao estiver cadastrado
     */
    private Ingresso buscarIngressoObrigatorio(String codigo) {
        Objects.requireNonNull(codigo, "O código não pode ser nulo.");

        Ingresso ingresso = ingressos.get(codigo);

        if (ingresso == null) {
            throw new IllegalArgumentException("Ingresso não encontrado.");
        }

        return ingresso;
    }

    /**
     * Verifica se o codigo do ingresso ja esta cadastrado.
     *
     * @param codigo codigo do ingresso
     */
    private void validarCodigoDuplicado(String codigo) {
        if (ingressos.containsKey(codigo)) {
            throw new IllegalArgumentException(
                    "Já existe um ingresso com esse código.");
        }
    }
}
