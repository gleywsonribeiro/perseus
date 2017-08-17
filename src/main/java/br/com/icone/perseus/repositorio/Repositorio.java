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
public class Repositorio implements Serializable {
    @Inject
    private EntityManager manager;
    
    public void save(Unidade unidade) {
        manager.getTransaction().begin();
        manager.merge(unidade);
        manager.getTransaction().commit();
    }
    
    public List<Unidade> listar() {
        return manager.createQuery("SELECT u FROM Unidade AS u", Unidade.class).getResultList();
    }
}
