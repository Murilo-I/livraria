package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ThemeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tema = "omega";

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String[] getTemas() {
		return new String[] { "afterdark", "afternoon", "afterwork", "aristo", "black-tie", "blitzer", "bluesky",
				"bootstrap", "casablanca", "cupertino", "cruze", "dark-hive", "delta", "dot-luv", "eggplant",
				"excite-bike", "flick", "glass-x", "home", "hot-sneaks", "humanity", "le-frog", "luna-amber",
				"luna-blue", "luna-green", "luna-pink", "midnight", "mint-choc", "nova-colored", "nova-dark",
				"nova-light", "omega", "overcast", "pepper-grinder", "redmond", "rocket", "sam", "smoothness",
				"south-street", "start", "sunny", "swanky-purse", "trontastic", "ui-darkness", "ui-lightness",
				"vader" };
	}
}
