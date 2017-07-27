/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;


import br.com.icone.perseus.modelo.Cliente;
import br.com.icone.perseus.modelo.TipoPessoa;
import br.com.icone.perseus.repositorio.Clientes;
import br.com.icone.perseus.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Gleywson
 */
@Named(value = "clienteController")
//@ViewScoped
@SessionScoped
public class ClienteController implements Serializable {

    private Cliente cliente;
//    private ClienteFilter filtro;
    private List<Cliente> clientesFiltrados;
    
    
    @Inject
    private Clientes clientes;
    
    public ClienteController() {
        cliente  = new Cliente();
//        this.filtro = new ClienteFilter();
        this.clientesFiltrados = new ArrayList<Cliente>();
    }
    
    public void novo() {
        this.cliente = new Cliente();
//        this.filtro = new ClienteFilter();
        this.clientesFiltrados = new ArrayList<Cliente>();
    }
    
    public void remover() {
//        repositorio.remove(cliente);
        JsfUtil.addMessage("Cliente removido com sucesso!");
        this.clientesFiltrados = null;
    }
    
    public void salvar() {
        if(cliente.getId() == null) {
            clientes.salvar(cliente);
        } else {
            clientes.editar(cliente);
        }
        this.cliente = new Cliente();
//        this.filtro = new ClienteFilter();
        this.clientesFiltrados = new ArrayList<Cliente>();
        JsfUtil.addMessage("Cliente inserido com sucesso!");
    }

    public void pesquisar() {
//        this.clientesFiltrados = clientes.getClientesFiltrados(filtro);
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

//    public ClienteFilter getFiltro() {
//        return filtro;
//    }
//
//    public void setFiltro(ClienteFilter filtro) {
//        this.filtro = filtro;
//    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }
    
    public TipoPessoa[] getTiposPessoa() {
        return TipoPessoa.values();
    }
    
}
