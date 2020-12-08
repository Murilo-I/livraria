package br.com.caelum.livraria.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class JSFUtil {

	@Produces
	@RequestScoped
	public FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
}
