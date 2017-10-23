/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.repositorio;

import br.com.icone.perseus.modelo.Unidade;
import br.com.icone.perseus.util.jsf.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author Gleywson
 */
public class UnidadeRepository implements Serializable {

    @Inject
    private EntityManager manager;

    public void guardar(Unidade unidade) {
        manager.merge(unidade);
    }

    public List<Unidade> listar() {
        return manager.createQuery("SELECT u FROM Unidade AS u", Unidade.class).getResultList();
    }

    public void remover(Unidade unidade) {
        try {
            unidade = porId(unidade.getId());
            manager.remove(unidade);
            manager.flush();
        } catch (PersistenceException e) {
//            JsfUtil.addErrorMessage("Não pode ser excluído!");
        }
    }
    
    public Unidade porId(Long id) {
        return manager.find(Unidade.class, id);
    }
}
