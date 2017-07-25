/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.martan.modelo.ContaPagar;
import br.com.icone.martan.modelo.repositorio.ContaPagarFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "contaPagarController")
@SessionScoped
public class ContaPagarController implements Serializable {
    @Inject
    private ContaPagarFacade repositorio;
    
    private ContaPagar conta;
    
    public ContaPagarController() {
        conta = new ContaPagar();
    }

    public ContaPagar getConta() {
        return conta;
    }

    public void setConta(ContaPagar conta) {
        this.conta = conta;
    }

    public void novo() {
        this.conta = new ContaPagar();
    }
    
    public void salvar() {
        if(conta.getId() == null) {
            repositorio.create(conta);
        } else {
            repositorio.edit(conta);
        }
        JsfUtil.addMessage("Conta salva com sucesso!");
        this.conta = new ContaPagar();
    }
    
    public String confirmarPagamento() {
        this.conta.setDataPagamento(new Date());
        salvar();
        return "pesquisa?faces-redirect=true";
    }
    
    public void remover() {
        repositorio.remove(conta);
        this.conta = new ContaPagar();
    }
}
