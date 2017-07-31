/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.repositorio;

import br.com.icone.perseus.modelo.Unidade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Gleywson
 */
public class Unidades implements Repositorio<Unidade, Long>, Serializable {

    @Inject
    private EntityManager manager;
    
    @Override
    public void salvar(Unidade entidade) {
        manager.persist(entidade);
    }

    @Override
    public void editar(Unidade entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Unidade entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Unidade porId(Class<Unidade> classe, Long pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Unidade> listarTodos(Class<Unidade> classe) {
        return manager.createQuery("SELECT u FROM Unidade u", classe).getResultList();
//        createQuery("SELECT conta FROM Pagamento conta WHERE conta.pedido IS NOT NULL and conta.dataPagamento IS NULL", Pagamento.class).getResultList();
    }
    
}
