package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Estado;

@FacesConverter("estadoConverter")
public class estadoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		//System.out.println("oi !" + value);
		
		if (value != null && !value.isEmpty()) {
            return (Estado) uic.getAttributes().get(value);
        }
        return null;
		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		
        if (object instanceof Estado) {
        	Estado entity= (Estado) object;
            if (entity != null && entity instanceof Estado && entity.getId() != null) {
                uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }

}
