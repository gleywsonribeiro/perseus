/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.martan.modelo.Categoria;
import br.com.icone.martan.modelo.Marca;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.modelo.repositorio.filter.ProdutoFilter;
import br.com.icone.martan.util.jsf.JsfUtil;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Gleywson
 */
@Named(value = "cadastroProdutoController")
@ConversationScoped
public class CadastroProdutoController implements Serializable {

    private Produto produto;
    private ProdutoFilter filtro;
    private List<Produto> produtosFiltrados;

    @Inject
    private ProdutoFacade repositorio;

    @Inject
    private Conversation conversacao;

    public CadastroProdutoController() {
        this.produto = new Produto();
        this.filtro = new ProdutoFilter();
        this.produtosFiltrados = new ArrayList<Produto>();
    }

    public String validar() {
        if (conversacao.isTransient()) {
            return "pesquisaProduto?faces-redirect=true";
        }
        return null;
    }

    public void salvar() {
        if (produto.getId() == null) {
            repositorio.create(produto);
        } else {
            repositorio.edit(produto);
        }
        this.produto = new Produto();
        this.produtosFiltrados = null;
        JsfUtil.addMessage("Salvo com sucesso!");
    }
    
    public void pesquisar() {
        this.produtosFiltrados = repositorio.getProdutosFiltrados(filtro);
    }

    public void categoriaSelecionada(SelectEvent event) {
        Categoria categoria = (Categoria) event.getObject();
        produto.setCategoria(categoria);
    }
    public void marcaSelecionada(SelectEvent event) {
        Marca marca = (Marca) event.getObject();
        produto.setMarca(marca);
    }
    public String novo() {
        this.produto = new Produto();
        return "cadastroProduto?faces-redirect=true";
    }

    public String editar() {
        iniciar();
        return "cadastroProduto?faces-redirect=true";
    }

    public void remover() {
        repositorio.remove(produto);
        produtosFiltrados = null;
        produto = new Produto();
        JsfUtil.addMessage("Produto removido com sucesso!");
    }

    public ProdutoFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(ProdutoFilter filtro) {
        this.filtro = filtro;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public void gerarEtiquetas() {
        List<Produto> produtosSemCodBarra = repositorio.getProdutosSemCodigoBarras();

        if (produtosSemCodBarra.isEmpty()) {
            JsfUtil.addErrorMessage("Não existem produtos para etiquetar!");
        } else {
            for (Produto prod : produtosSemCodBarra) {
                prod.setCodigoDeBarras(prod.getId());
                repositorio.edit(prod);
            }
            JsfUtil.addMessage("Códigos de barras gerados com sucesso!");
        }

    }

    public void iniciar() {
        if (conversacao.isTransient()) {
            conversacao.begin();
        }
    }

    public String finalizar() {
        if (!conversacao.isTransient()) {
            conversacao.end();
        }
        return "pesquisaProduto?faces-redirect=true";
    }

}
