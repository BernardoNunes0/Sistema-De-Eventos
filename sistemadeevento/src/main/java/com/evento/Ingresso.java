package com.evento;

import java.util.Objects;

/** Representa um ingresso vinculado a um evento e a um titular. */
public final class Ingresso {

    private final String codigo;
    private final Evento evento;
    private Participante titular;
    private SituacaoIngresso situacao = SituacaoIngresso.EMITIDO;

    /**
     * Cria um ingresso para o evento e o titular informados.
     *
     * @param codigo  código informado para o ingresso
     * @param evento  evento vinculado ao ingresso
     * @param titular participante titular do ingresso
     * @throws IllegalArgumentException se o código for vazio ou contiver apenas
     *                                  espaços
     * @throws NullPointerException     se o código, o evento ou o titular for nulo
     */
    public Ingresso(String codigo, Evento evento, Participante titular) {
        Objects.requireNonNull(codigo, "O código do ingresso não pode ser nulo.");
        if (codigo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O código do ingresso não pode ser vazio ou conter apenas espaços.");
        }

        this.codigo = codigo;
        this.evento = Objects.requireNonNull(evento, "O evento do ingresso não pode ser nulo.");
        this.titular = Objects.requireNonNull(titular, "O titular do ingresso não pode ser nulo.");
    }

    /**
     * Retorna o código do ingresso.
     *
     * @return código informado na criação
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna o evento vinculado ao ingresso.
     *
     * @return evento do ingresso
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Retorna o titular do ingresso.
     *
     * @return participante titular
     */
    public Participante getTitular() {
        return titular;
    }

    /**
     * Retorna a situação atual do ingresso.
     *
     * @return situação atual do ingresso
     */
    public SituacaoIngresso getSituacao() {
        return situacao;
    }

    /**
     * Marca o ingresso como utilizado.
     *
     * @throws IllegalStateException se a situação atual impedir o uso
     */
    public void marcarComoUtilizado() {
        exigirIngressoEmitido("Somente ingressos emitidos podem ser utilizados.");
        situacao = SituacaoIngresso.UTILIZADO;
    }

    /**
     * Cancela o ingresso.
     *
     * @throws IllegalStateException se a situação atual impedir o cancelamento
     */
    public void cancelar() {
        exigirIngressoEmitido("Somente ingressos emitidos podem ser cancelados.");
        situacao = SituacaoIngresso.CANCELADO;
    }

    /**
     * Altera o titular do ingresso.
     *
     * @param novoTitular participante que assume a titularidade
     * @throws IllegalStateException se a situação atual impedir a troca de titular
     * @throws NullPointerException  se o novo titular for nulo
     */
    public void alterarTitular(Participante novoTitular) {
        exigirIngressoEmitido("Somente ingressos emitidos podem ter o titular alterado.");
        this.titular = Objects.requireNonNull(novoTitular, "O novo titular não pode ser nulo.");
    }

    /**
     * Exige que o ingresso esteja emitido antes da operação.
     *
     * @param mensagem mensagem de erro da operação
     * @throws IllegalStateException se a situação do ingresso for diferente de
     *                               EMITIDO
     */
    private void exigirIngressoEmitido(String mensagem) {
        if (situacao != SituacaoIngresso.EMITIDO) {
            throw new IllegalStateException(mensagem);
        }
    }
}
