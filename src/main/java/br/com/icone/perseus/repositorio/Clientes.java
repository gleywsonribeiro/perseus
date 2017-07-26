/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.repositorio;

import br.com.icone.perseus.modelo.Cliente;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Gleywson
 */
public class Clientes implements Repositorio<Cliente, Long> {

    @Inject
    private EntityManager manager;

    @Override
    public void salvar(Cliente entidade) {
        manager.persist(entidade);
    }

    @Override
    public void remover(Cliente entidade) {
        manager.remove(entidade);
    }

    @Override
    public Cliente porId(Class<Cliente> classe, Long pk) {
        try {
            return manager.find(classe, pk);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Cliente> listarTodos(Class<Cliente> classe) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        return getEntityManager().createQuery(cq).getResultList();
        return manager.createQuery("select c from " + classe.getSimpleName() + " c").getResultList();
    }

    @Override
    public void editar(Cliente entidade) {
        manager.merge(entidade);
    }

}
