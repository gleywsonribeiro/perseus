/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.repositorio;

import java.io.Serializable;
import java.util.List;

public interface Repositorio<T, I extends Serializable> {

    public void salvar(T entidade);
    
    public void editar(T entidade);

    public void remover(T entidade);

    public T porId(Class<T> classe, I pk);

    public List<T> listarTodos(Class<T> classe);
}
