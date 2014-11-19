/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author qq
 */
import abstracts.EntityBase;
import abstracts.DataAccess;
import entity.Role;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Query;

@ManagedBean(name="roleService", eager = true)
@ApplicationScoped
public class RoleDao extends EntityBase implements DataAccess<Role> {
    private Object getAll;

    @Override
    public void insert(Role obj) {
        connect();
        session.save(obj);
        session.flush();
        disconnect();
    }

    @Override
    public void update(Role obj) {
        connect();
        session.update(obj);
        session.flush();
        disconnect();
    }

    @Override
    public void delete(Role obj) {
        connect();
        session.delete(obj);
        session.flush();
        disconnect();
    }

    @Override
    public List<Role> getAll() {
        connect();
        List<Role> dataList = session.createQuery("from Role").list();
        disconnect();
        
        for (Role cp : dataList) {
            System.out.println("Role : " + cp.role_name);
        }
        return dataList;
    }
    
    @Override
    public Role getById(Long id) {
        connect();
        List<Role> dataList = getByProperty("id", id);
        disconnect();
        
        if(dataList != null && dataList.size() > 0){
            return dataList.get(0);
        }
        
        return null;
    }

    @Override
    public List<Role> getByProperty(String name, Object value) {
        connect();
        Query query = session.createQuery("from Role Where " + name + " = :value");
        query.setParameter("value", value);
        List<Role> dataList = query.list();
        disconnect();
        return dataList;
    }

    @Override
    public List<Role> search(String name, Object value) {
        connect();
        Query query = session.createQuery("from Role Where " + name + " LIKE :value");
        query.setParameter("value", value);
        List<Role> dataList = query.list();
        disconnect();
        return dataList;
    }
}
