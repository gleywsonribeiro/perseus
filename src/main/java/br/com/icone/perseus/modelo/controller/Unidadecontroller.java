/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;


import br.com.icone.perseus.modelo.Unidade;
import br.com.icone.perseus.repositorio.Repositorio;
//import br.com.icone.perseus.repositorio.Unidades;
import br.com.icone.perseus.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "unidadecontroller")
@ViewScoped
public class Unidadecontroller implements Serializable {
    
    private Unidade unidade;
    private List<Unidade> unidades;
    
    @Inject
    private Repositorio repositorio;
    
    public Unidadecontroller() {
        unidade = new Unidade();
    }
    
    public void salvar() {
        repositorio.save(unidade);
        unidade = new Unidade();
        unidades = null;
        JsfUtil.addMessage("Unidade salva com sucesso!");
    }
    
    public void remover() {
        repositorio.remove(unidade);
        unidade = new Unidade();
        unidades = null;
        JsfUtil.addMessage("Removido com sucesso!");
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public List<Unidade> getUnidades() {
        if(unidades == null) {
            unidades = repositorio.listar();
        } 
        return unidades;
    }

}
