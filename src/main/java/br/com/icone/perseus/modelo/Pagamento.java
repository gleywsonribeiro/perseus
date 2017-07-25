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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author raque
 */
@Entity
public class Pagamento implements Serializable {

    @OneToMany(mappedBy = "pagamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Parcela> parcelas;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 50, name = "forma_pagamento")
    private TipoPagamento formaPagamento;
    
    @Column(name = "dt_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVencimento;
    
    @Column(name = "dt_pagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor = BigDecimal.ZERO;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal desconto = BigDecimal.ZERO;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal juros = BigDecimal.ZERO;
    
    
    
    /**
    Esses campos podem ser removidos na próxima atualização
    */
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal dinheiro = BigDecimal.ZERO;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal credito = BigDecimal.ZERO;
    
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal debito = BigDecimal.ZERO;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cheque = BigDecimal.ZERO;
    
    
    @Column(columnDefinition = "text")
    private String observacao;
    
    @OneToOne
    private Pedido pedido;
    
    

    public Pagamento() {
        this.parcelas = new ArrayList<Parcela>();
    }

    public BigDecimal getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(BigDecimal dinheiro) {
        this.dinheiro = dinheiro;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    public BigDecimal getDebito() {
        return debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public BigDecimal getCheque() {
        return cheque;
    }

    public void setCheque(BigDecimal cheque) {
        this.cheque = cheque;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public TipoPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(TipoPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getJuros() {
        return juros;
    }

    public void setJuros(BigDecimal juros) {
        this.juros = juros;
    }
    
    public BigDecimal getTroco(){
        BigDecimal totalPagamentos = dinheiro.add(credito).add(debito).add(cheque);
        return totalPagamentos.subtract(this.valor);
    }
    
    public BigDecimal getTrocoAbsoluto() {
        return getTroco().abs();
    }
    
    public String getStatusTroco() {
        if(getTroco().compareTo(BigDecimal.ZERO) > 0) {
            return "Troco";
        }
        else {
            return "Falta";
        }
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
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.icone.martan.modelo.ContaReceber[ id=" + id + " ]";
    }
    
}
