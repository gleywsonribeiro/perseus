/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.perseus.modelo.ContaPagar;
import br.com.icone.perseus.modelo.Fornecedor;
import br.com.icone.perseus.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "pesquisaContaPagar")
@ViewScoped
public class pesquisaContaPagar implements Serializable {

    @Inject
    private ContaPagarFacade repositorio;
    
    private Fornecedor filtro;
    
    private ContaPagar conta;

    public pesquisaContaPagar() {
        this.conta = new ContaPagar();
        this.filtro = new Fornecedor();
    }

    public List<ContaPagar> getContas() {
        return repositorio.findAll();
    }

    public List<ContaPagar> getContasPendentes() {
        return repositorio.contasPendentes();
    }
    
    public void pagar() {
        this.conta.setDataPagamento(new Date());
        this.conta.setValorPago(conta.getValor());
        repositorio.edit(conta);
        JsfUtil.addMessage("Conta " + conta.getId() + " paga com sucesso!");
        this.conta = new ContaPagar();
    }
   
    public void pesquisar() {
        
    }

    public ContaPagar getConta() {
        return conta;
    }

    public void setConta(ContaPagar conta) {
        this.conta = conta;
    }

    public Fornecedor getFiltro() {
        return filtro;
    }

    public void setFiltro(Fornecedor filtro) {
        this.filtro = filtro;
    }
    
}
