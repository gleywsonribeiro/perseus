/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.martan.modelo.Pagamento;
import br.com.icone.martan.modelo.repositorio.PagamentoFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author raque
 */
@Named(value = "contaReceberController")
@ViewScoped
public class PagamentoController implements Serializable {

    @Inject
    PagamentoFacade repositorio;
    
    private Pagamento conta;
    
    public PagamentoController() {
        this.conta = new Pagamento();
    }

    public Pagamento getConta() {
        return conta;
    }

    public void setConta(Pagamento conta) {
        this.conta = conta;
    }
    
    public void salvar() {
        if(conta.getId() == null) {
            repositorio.create(conta);
        }
        else {
            repositorio.edit(conta);
        }
        JsfUtil.addMessage("Conta salva com sucesso!");
        conta = new Pagamento();
    }
    
}
