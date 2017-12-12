package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Setor;

@FacesConverter("setorConverter")
public class setorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		
		if (value != null && !value.isEmpty()) {
            return (Setor) uic.getAttributes().get(value);
        }
        return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		
        if (object instanceof Setor) {
        	Setor entity= (Setor) object;
            if (entity != null && entity instanceof Setor && entity.getId() != null) {
                uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }

}
