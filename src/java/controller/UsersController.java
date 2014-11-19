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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name="UsersController")
@RequestScoped
@SessionScoped
public class UsersController {
    List<Users> userList = new ArrayList<Users>();
    Users users = new Users();
    UserDao userDao = new UserDao();
    String username;
    String password;
    Users userInfo = new Users();
    Users newUser;
        
    public UsersController(){
        userList = userDao.getAll();
        newUser = new Users();
    }

    public Users getNewUser() {
        return newUser;
    }

    public void setNewUser(Users newUser) {
        this.newUser = newUser;
    }
    
    public String doSave(){
        userDao.insert(newUser);
        FacesMessage message = new FacesMessage("Data berhasil ditambahkan");
        message.setSeverity((FacesMessage.SEVERITY_INFO));
        
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "user.list";
    }
    
    public String displayAll(){
        return "home";
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
    
    public Users getUserInfo(){
        Users userInfo = (Users) Util.getUserInfo();
        return userInfo;
    }
    
    
    public String loginSystem(){
        users = userDao.login(username, password);
        if(users != null){
            HttpSession sesiUser = Util.getSession();
            sesiUser.setAttribute("userInfo", users);
            return "home?faces-redirect=true";
        } else {        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Invalid Login!","Please Try Again!"));
            return "login";
        }
    }
    
    public String logout(){
        HttpSession sesiUser = Util.getSession();
        sesiUser.invalidate();
        return "login?faces-redirect=true";
    }
    
    public void coomingsoon(){
        Util.addMessage("Menu ini akan tampil \n pada pekerjaan mendatang","");
    }
     
    public void formAdd() {
        try {
            RequestContext.getCurrentInstance().openDialog("user.add");
            System.out.println("Show dialog");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
//        Map<String,Object> options = new HashMap<String, Object>();
//        options.put("modal", true);
//        options.put("draggable", false);
//        options.put("resizable", false);
//        options.put("contentHeight", 320);
//        RequestContext.getCurrentInstance().openDialog("user.add", options, null);
    }
}
