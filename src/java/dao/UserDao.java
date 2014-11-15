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
import entity.Users;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;

public class UserDao extends EntityBase implements DataAccess<Users> {

    @Override
    public void insert(Users obj) {
        connect();
        session.save(obj);
        session.flush();
        disconnect();
    }

    @Override
    public void update(Users obj) {
        connect();
        session.update(obj);
        session.flush();
        disconnect();
    }

    @Override
    public void delete(Users obj) {
        connect();
        session.delete(obj);
        session.flush();
        disconnect();
    }

    @Override
    public List<Users> getAll() {
        connect();
        List<Users> userList = session.createQuery("from Users").list();
        disconnect();
        return userList;
    }

    @Override
    public Users getById(int id) {
        connect();
        List<Users> dataList = getByProperty("id", id);
        disconnect();
        
        if(dataList != null && dataList.size() > 0){
            return dataList.get(0);
        }
        
        return null;
    }

    @Override
    public Users getById(String id) {
        connect();
        List<Users> dataList = getByProperty("id", id);
        disconnect();
        
        if(dataList != null && dataList.size() > 0){
            return dataList.get(0);
        }
        
        return null;
    }

    @Override
    public List<Users> getByProperty(String name, Object value) {
        connect();
        Query query = session.createQuery("from Users Where " + name + " = :value");
        query.setParameter("value", value);
        List<Users> dataList = query.list();
        disconnect();
        return dataList;
    }

    @Override
    public List<Users> search(String name, Object value) {
        connect();
        Query query = session.createQuery("from Users Where " + name + " LIKE :value");
        query.setParameter("value", value);
        List<Users> dataList = query.list();
        disconnect();
        return dataList;
    }
    
    public Users login(String username, String password){
        connect();
        Query query = session.createQuery("from Users Where username = :uname AND password = :pass");
        query.setParameter("uname", username);
        query.setParameter("pass", password);
        List<Users> dataList = query.list();
        disconnect();
        if(dataList != null && dataList.size() > 0){
            return dataList.get(0);
        } else {
            return null;
        }
    }
    
}
