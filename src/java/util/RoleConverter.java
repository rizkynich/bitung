/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author qq
 */

import entity.Role;
import dao.RoleDao;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
  
@FacesConverter("roleConverter")
public class RoleConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        System.out.println("data role " + value);
        if(value != null && value.trim().length() > 0) {
            try {
                RoleDao service = (RoleDao) fc.getExternalContext().getApplicationMap().get("roleService");
                //return service.getAll().get(Integer.parseInt(value));
                return service.getById(value);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid role."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Role) object).getId());
        }
        else {
            return null;
        }
    }   
} 