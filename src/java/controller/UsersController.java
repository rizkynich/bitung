/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author qq
 */
import abstracts.Util;
import entity.Users;
import dao.UserDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="UsersController")
@RequestScoped
@SessionScoped
public class UsersController {
    List<Users> userList = new ArrayList<Users>();
    Users users = new Users();
    UserDao userDao = new UserDao();
    String username;
    String password;
    
    public UsersController(){
        userList = userDao.getAll();
    }
    
    public String displayAll(){
        return "index";
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String loginSystem(){
        users = userDao.login(username, password);
        if(users != null){
            HttpSession sesiUser = Util.getSession();
            sesiUser.setAttribute("userInfo", users);
            return "home";
        } else {        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Invalid Login!","Please Try Again!"));
            return "index";
        }
    }
    
    public String logout(){
        HttpSession sesiUser = Util.getSession();
        sesiUser.invalidate();
        return "index?faces-redirect=true";
    }
    
    public void coomingsoon(){
        Util.addMessage("Menu ini akan tampil \n pada pekerjaan mendatang","");
    }
}
