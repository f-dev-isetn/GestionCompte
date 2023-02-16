package com.iset.converters;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.iset.dao.CompteDAO;
import com.iset.entities.Compte;


@FacesConverter(forClass=Compte.class)
public class CompteConverter2 implements Converter {
	 @Override
	    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String value) {        	    
           if ( value != null && value.trim().length() > 0 ) {
                try {
                	CompteDAO cltDao = new CompteDAO();
                    Compte cl= cltDao.consulter( Integer.parseInt( value ) );
                    return cl;
                } catch ( Exception e ) {
                  
                    return null;
                }
            }
            else {
                return null;
            }
            
	    }

	    @Override
	    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
	      
	    	if (object != null)
	    	{	 
	           return  String.valueOf(  ((Compte) object).getNumCompte());
	    	}
	    	else
	    		return null;
	    }
  
}
