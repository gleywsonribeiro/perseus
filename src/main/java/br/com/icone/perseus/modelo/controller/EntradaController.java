/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.martan.modelo.Entrada;
import br.com.icone.martan.modelo.Fornecedor;
import br.com.icone.martan.modelo.ItemEntrada;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.TipoDocumentoFiscal;
import br.com.icone.martan.modelo.repositorio.EntradaFacade;
import br.com.icone.martan.modelo.repositorio.FornecedorFacade;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author raque
 */
@Named(value = "entradaController")
@SessionScoped
public class EntradaController implements Serializable {

    private Entrada entrada;
    private List<Entrada> entradas;

    private ItemEntrada item;

    @Inject
    private EntradaFacade repositorio;
    @Inject
    private FornecedorFacade repositorioFornecedor;
    @Inject
    private ProdutoFacade repositorioProduto;

    public EntradaController() {
        novo();
    }

    public void salvar() {
        
        if(entrada.getItens().isEmpty()) {
            JsfUtil.addErrorMessage("A nota precisa de pelo menos um item!");
            return;
        } 
        
        for(ItemEntrada itemDaNota:entrada.getItens()) {
            Produto p = itemDaNota.getProduto();
            
            int quantidadeProdutoNota  = itemDaNota.getQuantidade();
            int quantidadeProdutoEstoque = p.getEstoqueAtual();
            
            Double totalEstoque = p.getValorCusto().doubleValue() * quantidadeProdutoEstoque;
            Double totalNota = itemDaNota.getValorUnitario().doubleValue() * quantidadeProdutoNota;
            
            
            Double custoMedio = (totalEstoque + totalNota) / (quantidadeProdutoEstoque + quantidadeProdutoNota);
            
            //Atualiza o estoque e o custo médio
            p.adicionar(quantidadeProdutoNota);
            p.setValorCusto(new BigDecimal(custoMedio.toString()));
            
            repositorioProduto.edit(p);
        }
        
        if (entrada.getId() == null) {
            repositorio.create(entrada);
            JsfUtil.addMessage("Entrada registrada com sucesso!");
        } else {
            repositorio.edit(entrada);
            JsfUtil.addMessage("Entrada alterada com sucesso!");
        }
//        entrada = new Entrada();

        entradas = null;
    }
    
    public void adicionarItem() {
        if (entrada.isExisteItem(item)) {
            JsfUtil.addWarnMessage("Item já existe!");
        } else {
            entrada.getItens().add(item);
            item.setEntrada(entrada);
        }

        entrada.recalcularTotalNota();
        this.item = new ItemEntrada();
    }

    public void removerItem() {
        entrada.getItens().remove(item);
        this.item = new ItemEntrada();
        entrada.recalcularTotalNota();
    }

    public void novo() {
        this.entrada = new Entrada();
        this.item = new ItemEntrada();
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public List<Fornecedor> getFornecedores() {
        return repositorioFornecedor.findAll();
    }

    public TipoDocumentoFiscal[] getDocFiscais() {
        return TipoDocumentoFiscal.values();
    }

    public List<Entrada> getEntradas() {
        if (entradas == null) {
            entradas = repositorio.findAll();
        }
        return entradas;
    }

    public ItemEntrada getItem() {
        return item;
    }

    public void setItem(ItemEntrada item) {
        this.item = item;
    }

    public List<Produto> buscaProdutoDescricao(String descricao) {
        return repositorioProduto.getProdutosPorDescricao(descricao);
    }
    
    public List<Produto> buscaProdutoCodigo(String cod) {
        return repositorioProduto.getProdutosPorCodigo(cod);
    }
}
