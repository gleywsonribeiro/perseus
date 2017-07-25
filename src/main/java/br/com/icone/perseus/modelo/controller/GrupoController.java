/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.martan.modelo.Grupo;
import br.com.icone.martan.modelo.repositorio.GrupoFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "grupoController")
@ViewScoped
public class GrupoController implements Serializable {
    @Inject
    private GrupoFacade repositorio;
    private Grupo grupo;
    private List<Grupo> grupos;
    
    public GrupoController() {
        this.grupo = new Grupo();
    }
    
    public void salvar() {
        if(this.grupo.getId() == null) {
            repositorio.create(grupo);
        } else {
            repositorio.edit(grupo);
        }
        JsfUtil.addMessage("Salvo com sucesso!");
        grupo = new Grupo();
        grupos = null;
    }
    
    public void remover() {
        repositorio.remove(grupo);
        JsfUtil.addMessage("Excluído com sucesso!");
        grupo = new Grupo();
        grupos = null;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getGrupos() {
        if(grupos == null) {
            grupos = repositorio.findAll();
        }
        return grupos;
    }
    
    
}
