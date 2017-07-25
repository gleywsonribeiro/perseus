/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.martan.modelo.Marca;
import br.com.icone.martan.modelo.repositorio.MarcaFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gleywson
 */
@Named(value = "marcaController")
@ViewScoped
public class MarcaController implements Serializable {

    @Inject
    private MarcaFacade repositorio;

    private Marca marca;
    private List<Marca> marcas;

    public MarcaController() {
        this.marca = new Marca();
    }

    public void salvar() {
        if (this.marca.getId() == null) {
            repositorio.create(marca);
        } else {
            repositorio.edit(marca);
        }
//        JsfUtil.addMessage("Salvo com sucesso!");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso", "Salvo!");

        RequestContext.getCurrentInstance().showMessageInDialog(message);
        marca = new Marca();
        marcas = null;
    }

    public void excluir() {
        repositorio.remove(marca);
        this.marca = new Marca();
        this.marcas = null;
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão", "Registro removido com sucesso!");
        
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public List<Marca> getMarcas() {
        if (this.marcas == null) {
            marcas = repositorio.findAll();
        }
        return marcas;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
