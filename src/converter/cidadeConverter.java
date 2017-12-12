package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Cidade;

@FacesConverter("cidadeConverter")
public class cidadeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		System.out.println("oi !" + value);
		
		if (value != null && !value.isEmpty()) {
            return (Cidade) uic.getAttributes().get(value);
        }
        return null;
		
//		if(value != null && !value.isEmpty()){
//			
//			try{
//				DaoCidade dao = new DaoCidade();
//				Cidade c = dao.findById(Integer.parseInt(value)); 
//				System.out.println(c.getNome() + "!!!!!");
//				
//				return c;
//			}catch(NumberFormatException e){
//				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a Valid city")); 
//			}			
//		}
//		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        
//		if (object instanceof Cidade) {
//            Cidade entity= (Cidade) object;
//            if (entity != null && entity instanceof Cidade) {
//                uic.getAttributes().put(String.valueOf(entity.getId()), entity);
//               
//                return String.valueOf(entity.getId());                
//            }
//        }
//        return "";
		
        if (object instanceof Cidade) {
        	Cidade entity= (Cidade) object;
            if (entity != null && entity instanceof Cidade && entity.getId() != null) {
                uic.getAttributes().put( entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }

}
