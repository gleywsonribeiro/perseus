/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.repositorio;

import br.com.icone.perseus.modelo.Unidade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Gleywson
 */
public class RepositorioUnidade implements IRepository<Unidade>, Serializable {
    
    @Inject
    private EntityManager entityManager;
    
    @Override
    public Unidade porId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar(Unidade entity) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    public void alterar(Unidade entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Unidade entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Unidade> listarTudo() {
        return getEntityManager().createQuery("SELECT u FROM Unidade AS u", Unidade.class).getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    
}
