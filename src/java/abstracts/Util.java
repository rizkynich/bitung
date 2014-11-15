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

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
 
    public static HttpSession getSession() {
      return (HttpSession)
        FacesContext.
        getCurrentInstance().
        getExternalContext().
        getSession(false);
    }

    public static HttpServletRequest getRequest() {
     return (HttpServletRequest) FacesContext.
        getCurrentInstance().
        getExternalContext().getRequest();
    }

    public static Object getUserInfo()
    {
      HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      return  session.getAttribute("userInfo");
    }

    public static void addMessage(String summary, String detail) {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
