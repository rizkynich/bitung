/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstracts;

/**
 *
 * @author qq
 */

import java.io.Serializable;
import java.util.List;

public interface DataAccess<T> extends Serializable {
    
    public void insert(T obj);
    
    public void update(T obj);
    
    public void delete(T obj);
    
    public List<T> getAll();
    
    public T getById(Long id);
        
    public List<T> getByProperty(String name, Object value);
    
    public List<T> search(String name, Object value);
}
