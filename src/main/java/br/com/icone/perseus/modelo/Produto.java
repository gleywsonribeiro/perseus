/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gleywson
 */
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String sku;

    private boolean ativo;
    private boolean bloqueado;

    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private SubCategoria subCategoria;

    @ManyToOne
    private Marca marca;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Unidade unidade;

    //private List<Fornecedor> fornecedores;
    //Unidade
    //Barcode
    @Column(nullable = false, length = 75)
    private String descricao;

    @Column(nullable = false, name = "vl_venda", scale = 2, precision = 10)
    private BigDecimal valorVenda;

    @Column(nullable = false, name = "vl_custo", scale = 2, precision = 10)
    private BigDecimal valorCusto;

    @Column(name = "codigo_barras", length = 50)
    private String codigoDeBarras;

    @Column(nullable = false, name = "estoque_atual")
    private int estoqueAtual;

    @Column(nullable = false, name = "estoque_min")
    private int estoqueMinimo;

    @Column(nullable = false, name = "estoque_max")
    private int estoqueMaximo;

    public Produto() {
        this.ativo = true;
        this.valorCusto = BigDecimal.ZERO;
        this.valorVenda = BigDecimal.ZERO;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public int getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(int estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public void setCodigoDeBarras(long codigoDeBarras) {
        this.codigoDeBarras = String.format("%010d", codigoDeBarras);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public boolean isPossuiCodBarras() {
        return this.codigoDeBarras != null;
    }

    public void baixar(int quantidade) {
        if (this.estoqueAtual >= quantidade) {
            this.estoqueAtual -= quantidade;
        } else {
            System.out.println("Não foi possivel tirar do estoque!");
        }
    }

    public void adicionar(int quantidade) {
        this.estoqueAtual += quantidade;
    }

    @Override
    public String toString() {
        return "br.com.iconeinformartica.martan.modelo.Produto[ id=" + id + " ]";
    }

}
