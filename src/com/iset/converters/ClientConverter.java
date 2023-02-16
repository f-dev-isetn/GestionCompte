package com.iset.converters;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.iset.dao.ClientDao;
import com.iset.entities.Client;


@FacesConverter(forClass=Client.class)
public class ClientConverter implements Converter {
	 @Override
	    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String value) {        	    
           if ( value != null && value.trim().length() > 0 ) {
                try {
                	ClientDao cltDao = new ClientDao();
                    Client cl= cltDao.consulter( Integer.parseInt( value ) );
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
	           return  String.valueOf(  ((Client) object).getCin());
	    	}
	    	else
	    		return null;
	    }
  
}
