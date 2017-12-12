package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Grau;

@FacesConverter("grauConverter")
public class grauConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		
		if (value != null && !value.isEmpty()) {
            return (Grau) uic.getAttributes().get(value);
        }
        return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		
        if (object instanceof Grau) {
        	Grau entity= (Grau) object;
            if (entity != null && entity instanceof Grau && entity.getId() != null) {
                uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }

}
