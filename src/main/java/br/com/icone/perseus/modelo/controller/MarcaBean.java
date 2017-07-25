/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.martan.modelo.Categoria;
import br.com.icone.martan.modelo.Marca;
import br.com.icone.martan.modelo.repositorio.MarcaFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gleywson
 */
@Named(value = "marcaBean")
@RequestScoped
public class MarcaBean {
    
    @Inject
    private MarcaFacade facade;
    
    private Marca marca;
    private List<Marca> marcas;
    
    public MarcaBean() {
        marca  = new Marca();
    }
    
    public void salvar() {
        if(marca.getId() == null) {
            facade.create(marca);
        } else {
            facade.edit(marca);
        }
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Marca> getMarcas() {
        if(marcas == null) {
            marcas = facade.findAll();
        }
        return marcas;
    }
    
    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<String, Object>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 300);

        RequestContext.getCurrentInstance().openDialog("/telas/marca/dialogoMarca", opcoes, null);
    }
    
    public void selecionar(Marca marca) {
        RequestContext.getCurrentInstance().closeDialog(marca);
    }
    
    
    
    
    
}
