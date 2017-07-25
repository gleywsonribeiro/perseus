/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.martan.modelo.Categoria;
import br.com.icone.martan.modelo.TipoPagamento;
import br.com.icone.martan.modelo.SubCategoria;
import br.com.icone.martan.modelo.repositorio.CategoriaFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Gleywson
 */
@Named(value = "categoriaController")
@ViewScoped
public class CategoriaController implements Serializable {

    private Categoria categoria;
    private List<Categoria> categorias;
    private SubCategoria subCategoria;

    @Inject
    private CategoriaFacade repositorio;

    public CategoriaController() {
        categoria = new Categoria();
        subCategoria = new SubCategoria();
    }

    public void addSubCategoria() {
        subCategoria.setCategoria(categoria);
        categoria.getSubCategorias().add(subCategoria);
        subCategoria = new SubCategoria();
    }
    
    public void removeSubCategoria() {
        categoria.getSubCategorias().remove(subCategoria);
        subCategoria = new SubCategoria();
    }
    
    public void salvar() {
        if (categoria.getId() == null) {
            repositorio.create(categoria);
        } else {
            repositorio.edit(categoria);
        }
        categoria = new Categoria();
        categorias = null;
        JsfUtil.addMessage("Salvo!");
    }
    
    
    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<String, Object>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 300);

        RequestContext.getCurrentInstance().openDialog("/telas/categoria/dialogoCategoria", opcoes, null);
    }
    
    public void selecionar(Categoria categoria) {
        RequestContext.getCurrentInstance().closeDialog(categoria);
    }

    public void remover() {
        try {
            repositorio.remove(categoria);
            JsfUtil.addMessage("Categoria " + categoria.getNome() + " removida com sucesso!");
            categoria = new Categoria();
            categorias = null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Categoria " + categoria.getNome() + " não pode ser excluída!");
            categoria = new Categoria();
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public List<Categoria> getCategorias() {
        if (categorias == null) {
            categorias = repositorio.findAll();
        }
        return categorias;
    }

}
