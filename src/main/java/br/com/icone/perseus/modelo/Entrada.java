/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Gleywson
 */
@Entity
public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nr_documento", length = 50)
    private String numeroDocumento;
    @Column(length = 5)
    private int serie;
    
    @Enumerated(EnumType.STRING)
    TipoDocumentoFiscal documentoFiscal;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;
    
    @Column(name = "dt_emissao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmissao;
    
    @Column(name = "dt_entrada")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataEntrada;
    
    @Column(name = "vl_total", scale = 2, precision = 10)
    private BigDecimal valorTotal = BigDecimal.ZERO;
    
    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemEntrada> itens;

    public Entrada() {
        this.dataEntrada = new Date();
        itens = new ArrayList<ItemEntrada>();
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemEntrada> getItens() {
        return itens;
    }

    public void setItens(List<ItemEntrada> itens) {
        this.itens = itens;
    }

    public TipoDocumentoFiscal getDocumentoFiscal() {
        return documentoFiscal;
    }

    public void setDocumentoFiscal(TipoDocumentoFiscal documentoFiscal) {
        this.documentoFiscal = documentoFiscal;
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
        if (!(object instanceof Entrada)) {
            return false;
        }
        Entrada other = (Entrada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.icone.martan.modelo.Entrada[ id=" + id + " ]";
    }
    
    public void recalcularTotalNota() {
        BigDecimal total = BigDecimal.ZERO;
        
        for (ItemEntrada item : itens) {
            total = total.add(item.getValorTotal());
        }
        setValorTotal(total);
    }
    
    public boolean isExisteItem(ItemEntrada novo) {
        for (ItemEntrada item : itens) {
            if(item.getProduto().equals(novo.getProduto())) {
//                item.setQuantidade(item.getQuantidade() + novo.getQuantidade());
                return true;
            }
        }
        return false;
    }
    
    public boolean isNovo() {
        return id == null;
    }
    
    public boolean isEditavel() {
        return isNovo();
    }
    
    public boolean isNaoEditavel() {
        return !isEditavel();
    }
    
    
//    public void atualizarEstoque() {
//        for(ItemEntrada item: itens)
//    }
}
