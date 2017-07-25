/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author gleyw
 */

@Entity(name = "conta_pagar")
public class ContaPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dt_lancamento", nullable = false)
    private Date lancamento;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dt_vencimento", nullable = false)
    private Date dataVencimento;

    @Column(name = "nr_documento", length = 50)
    private String numeroDocumento;
    
    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false)
    private double valor;


    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dt_pagamento")
    private Date dataPagamento;
    
    @Column(name = "valor_desconto")
    private double valorDesconto;
    
    @Column(name = "valor_juros")
    private double valorJuros;
    
    @Column(name = "valor_pago")
    private double valorPago;
    
    @Column(columnDefinition = "text")
    private String observacao;
    
    

    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;

    public ContaPagar() {
        this.lancamento = new Date();
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(double valorJuros) {
        this.valorJuros = valorJuros;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataContaPagar) {
        this.dataVencimento = dataContaPagar;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    
    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isVencida() {
        return this.dataVencimento.before(new Date());
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
        if (!(object instanceof ContaPagar)) {
            return false;
        }
        ContaPagar other = (ContaPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ContaPagar[ id=" + id + " ]";
    }

}
