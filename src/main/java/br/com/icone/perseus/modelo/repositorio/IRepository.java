/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.repositorio;

import java.util.List;

/**
 *
 * @author Gleywson
 * @param <T>
 */
public interface IRepository<T> {

    public T porId(Long id);

    public void guardar(T entity);

    public void alterar(T entity);

    public void remover(T entity);

    public List<T> listarTudo();
}
