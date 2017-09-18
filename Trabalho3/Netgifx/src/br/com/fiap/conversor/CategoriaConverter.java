package br.com.fiap.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fiap.entity.Categoria;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return uiComponent.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof Categoria) {
			Categoria entity = (Categoria) value;
			if (entity instanceof Categoria && entity.getIdCategoria() != null) {
				uiComponent.getAttributes().put(entity.getIdCategoria().toString(), entity);
				return entity.getIdCategoria().toString();
			}
		}
		return "";
	}

}
