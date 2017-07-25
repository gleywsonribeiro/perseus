/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo;

/**
 *
 * @author Gleywson
 */
public enum TipoPedido {
    PEDIDO("Pedido"),
    VENDA("Venda");

    private final String descricao;

    TipoPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
