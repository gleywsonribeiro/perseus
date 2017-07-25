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
public enum TipoDocumentoFiscal {
    NOTA_FISCAL("Nota Fiscal");

    private TipoDocumentoFiscal(String descricao) {
        this.descricao = descricao;
    }
    
    private final String descricao;
    
    public String getDescricao() {
        return descricao;
    }
}
